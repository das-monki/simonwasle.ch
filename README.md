# Simon Wasle's Personal Website

A modern, SEO-friendly personal homepage built with Clojure/ClojureScript, featuring server-side rendering with client-side hydration. Deployed automatically to GitHub Pages.

## Technology Stack

**Core Technologies:**
- **Clojure/ClojureScript**: Full-stack development in a single language
- **UIx**: React wrapper for ClojureScript, enabling component-based UI development
- **Shadow-cljs**: ClojureScript build tool with excellent development experience
- **Server-Side Rendering (SSR)**: Pre-rendered HTML for optimal SEO and initial load performance
- **Client-Side Hydration**: React components become interactive after page load

**Why This Approach:**
- **SEO-Friendly**: Search engines receive fully rendered HTML content
- **Fast Initial Load**: Users see content immediately, before JavaScript loads
- **Progressive Enhancement**: Site works without JavaScript, enhances with it
- **Developer Experience**: Hot reloading, REPL-driven development, unified codebase

## Architecture

The site uses a hybrid rendering approach:

1. **Build Time**: Static HTML pages are generated with full content
2. **Runtime**: Minimal JavaScript loads to hydrate interactive components
3. **Best of Both Worlds**: SEO benefits of static sites + React's interactivity

Components are written in `.cljc` files, allowing them to render on both server and client. The server uses `uix.dom.server/render-to-string` for HTML generation, while the client uses `uix.dom/hydrate-root` for interactivity.

## Development

**Prerequisites:**
- [Nix package manager](https://nixos.org/download.html) (recommended)
- Or: Java 11+, Node.js 16+, Clojure CLI tools

**Quick Start:**
```bash
# Enter development environment (with Nix)
nix develop

# Start development servers (in separate terminals)
dev      # Shadow-cljs with hot reload
server   # Ring server for SSR preview

# Build for production
build    # Generates static site in docs/ for GitHub Pages
```

## Deployment

The site automatically deploys to GitHub Pages from the `docs/` directory. The build process:

1. Compiles ClojureScript to optimized JavaScript bundles
2. Renders all pages to static HTML with full content
3. Copies assets and generates the complete static site
4. GitHub Pages serves the static files with CDN acceleration

## Project Structure

```
├── src/app/pages/home.cljc    # Main page component (universal)
├── src/app/client/home.cljs   # Client-side hydration entry
├── src/main/render.clj        # SSR rendering and HTML templates
├── src/main/core.clj          # Development server
├── dev/build.clj              # Static site generator
├── shadow-cljs.edn            # ClojureScript build config
├── flake.nix                  # Nix development environment
└── docs/                      # Generated static site (GitHub Pages)
```

This setup provides a robust foundation for building fast, SEO-friendly single-page applications with modern tooling and deployment automation.