# Claude Development Guide for simonwasle.ch

## Repository Overview
Personal website for Simon Wasle built with Clojure/ClojureScript, featuring static site generation with client-side hydration. Deployed to GitHub Pages via the `docs/` directory.

## Tech Stack
- **Clojure/ClojureScript**: Full-stack development in `.cljc` files
- **UIx**: React wrapper for ClojureScript (`uix.core`)
- **Shadow-cljs**: ClojureScript compilation and bundling
- **Tailwind CSS v4**: Utility-first CSS framework with dark mode support
- **Nix**: Reproducible development environment
- **Babashka**: Task automation (if available)

## Key Development Commands
```bash
# Using Nix (recommended)
nix develop          # Enter development shell
dev                  # Start shadow-cljs with hot reload
server              # Start SSR development server
build               # Build static site to docs/
serve               # Serve static site locally

# Without Nix
bb dev              # Start shadow-cljs and SSR server
bb build            # Build static site
```

## Important Files and Patterns

### Component Structure
- **Universal components**: Written in `.cljc` files for server/client rendering
- **Reader conditionals**: Use `#?(:cljs ... :clj ...)` for platform-specific code
- **UIx syntax**: `($ :div {:class "..."} children)` for React elements

### Dark Mode Implementation
```clojure
;; Dark mode with Tailwind classes
{:class "text-gray-900 dark:text-white"}

;; State management pattern
(let [[dark-mode? set-dark-mode!] (use-state false)
      [hydrated? set-hydrated!] (use-state false)]
  ;; Sync with DOM after hydration to prevent mismatch
  (use-effect
    (fn []
      #?(:cljs
         (do
           (set-dark-mode! (.contains (.-classList js/document.documentElement) "dark"))
           (set-hydrated! true))))
    []))
```

### SVG Patterns
- Use `currentColor` for stroke to inherit text color: `{:class "stroke-current"}`
- Use Tailwind responsive utilities instead of CSS variables: `{:class "stroke-3 md:stroke-2"}`
- Invisible click areas: `{:class "fill-none stroke-none"}`
- Common SVG classes: `{:class "fill-none stroke-current"}`

## Build Process
1. **Tailwind CSS v4**: `@tailwindcss/cli` compiles CSS from `resources/css/input.css`
2. **Shadow-cljs**: Compiles ClojureScript to optimized JS bundles with CSS build hooks
3. **Server-side rendering**: `uix.dom.server/render-to-string` generates HTML
4. **Static generation**: `dev/build.clj` creates static HTML files
5. **Output**: Everything goes to `docs/` for GitHub Pages

## Common Gotchas

### Hydration Mismatches
- Server always renders initial state (e.g., light mode)
- Use `hydrated?` flag to show different content after hydration
- Example: `{:dark-mode? (and hydrated? dark-mode?)}`

### Dark Mode Flash (FOUC)
- Inline script in `<head>` sets dark class before React loads
- Uses localStorage and respects system preference
- Pattern from Tailwind docs prevents flash

### Tailwind v4 Configuration
- **No config file needed**: Tailwind v4 uses CSS-first configuration
- **Plugins in CSS**: Use `@plugin '@tailwindcss/typography';` instead of config file
- **Custom variants**: `@custom-variant dark (&:where(.dark, .dark *));`
- **Theme definitions**: Use `@theme` block in CSS for custom animations and utilities
- **Rebuild CSS**: Handled automatically by shadow-cljs build hooks

### Reader Conditionals
```clojure
;; Correct - returns value for both platforms
#?(:cljs js/undefined :clj nil)

;; Incorrect - causes "No such namespace: js" error in CLJ
js/undefined
```

## Testing Changes
1. **Development**: Changes hot-reload automatically
2. **Build test**: Run `nix develop --command build` or `bb build`
3. **Check output**: Look in `docs/` directory
4. **Common errors**: Check browser console for hydration warnings

## SEO Optimizations
- Meta tags and Open Graph in `src/main/render.clj`
- Structured data (JSON-LD) for Person schema
- `sitemap.xml` and `robots.txt` in `resources/public/`
- OG image at `/assets/images/og-image.png`

## Deployment
- Push changes to GitHub
- `docs/` directory serves via GitHub Pages
- Domain: simonwasle.ch
- No additional build step needed after local `build`

## Code Style
- No comments unless requested
- Match existing patterns in codebase
- **Prefer Tailwind utilities**: Use `{:class "stroke-3 md:stroke-2"}` over CSS variables
- **Clean component props**: Remove redundant attributes like `:fill "none"` when using classes
- **Responsive design**: Use Tailwind responsive prefixes (`md:`, `lg:`) instead of CSS media queries
- **Animation delays**: Use custom utility `animation-delay-*` for staggered animations
- Keep components in `.cljc` for universal rendering

## Useful Patterns

### Conditional Classes
```clojure
;; Simple conditional
{:class (if condition "class-a" "class-b")}

;; Multiple conditions
{:class (str "base-class "
             (when condition "conditional-class"))}
```

### LocalStorage with SSR
```clojure
;; Always use reader conditionals
#?(:cljs (.setItem js/localStorage "key" "value"))
```

### SVG with Tailwind Classes
```clojure
;; Modern approach with Tailwind v4
($ :svg
  {:class "fill-none stroke-current stroke-3 md:stroke-2"
   :viewBox "0 0 800 128"}
  ($ :path
    {:d "M0 85 Q100 70 200 85 T400 85"
     :class "animate-wave animation-delay-500"}))

;; Instead of old CSS variable approach
($ :svg
  {:fill "none"
   :stroke "currentColor"
   :style {:--wave-stroke "3"}}
  ($ :path
    {:d "M0 85 Q100 70 200 85 T400 85"
     :class "wave-animation"}))
```

### Tailwind v4 Animations and Theme
```css
/* Define animations in @theme block in input.css */
@theme {
  --animate-wave: wave 3s ease-in-out infinite;
  @keyframes wave {
    0%, 100% { transform: translateY(0px); }
    50% { transform: translateY(-8px); }
  }

  --animate-fade-in: fade-in 0.5s ease-in-out 0.2s forwards;
  @keyframes fade-in {
    from { opacity: 0; }
    to { opacity: 1; }
  }
}

/* Custom utilities */
@utility animation-delay-* {
  animation-delay: --value(integer)ms;
}
```

```clojure
;; Use in components with Tailwind utilities
{:class "animate-wave animation-delay-500"}
{:class "opacity-0 animate-fade-in"}
```

## Current Features
- Single page website with sections
- Dark/light mode toggle (click sun/moon)
- Animated SVG beach scene
- Mobile responsive design
- SEO optimized with meta tags
- No cookies, privacy-focused

## Tailwind v4 Best Practices

### CSS Organization
- **All styles in `resources/css/input.css`**: No inline styles in components or render files
- **Use `@theme` blocks**: Define custom animations and utilities in theme
- **Layer organization**: Use `@layer base`, `@layer components`, `@layer utilities`
- **Plugin imports**: Use `@plugin` directive instead of config file plugins

### Animation Patterns
- **Theme-based animations**: Define keyframes and animation variables in `@theme`
- **Utility classes**: Create reusable animation delay utilities
- **Component cleanup**: Replace CSS variables with Tailwind responsive classes
- **Staggered animations**: Use `animation-delay-*` utilities for wave effects

### Migration from v3 to v4
- **Remove config files**: Delete `tailwind.config.js`, move everything to CSS
- **Update build tools**: Use `@tailwindcss/cli` instead of `tailwindcss` CLI
- **CSS-first approach**: Define themes, plugins, and utilities directly in CSS
- **Cleaner components**: Remove redundant SVG attributes, use class-based styling

### Component Refactoring
- **Remove style objects**: Convert `:style` props to Tailwind classes
- **Consolidate SVG props**: Use `{:class "fill-none stroke-current"}` pattern
- **Responsive utilities**: Replace CSS variables with `stroke-3 md:stroke-2` patterns
- **Animation classes**: Use semantic names like `animate-wave` with delay utilities

## Remember
- Always check for hydration warnings in browser console
- Test dark mode switching after changes
- Verify mobile responsiveness
- Build before committing to ensure static site generates correctly
- Use Tailwind v4's CSS-first approach for all styling
