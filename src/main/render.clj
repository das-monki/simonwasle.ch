(ns main.render
  (:require [uix.core :refer [$]]
            [uix.dom.server :as dom.server]
            [app.pages.home :as home]))

(defn html-template
  [{:keys [title content script-src meta-description]
    :or {title "UIx Static Site"
         meta-description "A static site built with Clojure, shadow-cljs and UIx"}}]
  (str "<!DOCTYPE html>\n"
       "<html lang=\"en\">\n"
       "<head>\n"
       "  <meta charset=\"UTF-8\">\n"
       "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
       "  <meta name=\"description\" content=\"" meta-description "\">\n"
       "  <title>" title "</title>\n"
       "  <meta name=\"author\" content=\"Simon Wasle\">\n"
       "  <meta name=\"keywords\" content=\"Simon Wasle, software engineer, developer, entrepreneur, maker, full-stack development, system architecture, Basel, Switzerland\">\n"
       "  <link rel=\"canonical\" href=\"https://simonwasle.ch\">\n"
       "  <!-- Open Graph / Facebook -->\n"
       "  <meta property=\"og:type\" content=\"website\">\n"
       "  <meta property=\"og:url\" content=\"https://simonwasle.ch\">\n"
       "  <meta property=\"og:title\" content=\"" title "\">\n"
       "  <meta property=\"og:description\" content=\"" meta-description "\">\n"
       "  <meta property=\"og:image\" content=\"https://simonwasle.ch/assets/images/og-image.png\">\n"
       "  <meta property=\"og:image:width\" content=\"1200\">\n"
       "  <meta property=\"og:image:height\" content=\"630\">\n"
       "  <meta property=\"og:site_name\" content=\"Simon Wasle\">\n"
       "  <!-- Twitter -->\n"
       "  <meta property=\"twitter:card\" content=\"summary_large_image\">\n"
       "  <meta property=\"twitter:url\" content=\"https://simonwasle.ch\">\n"
       "  <meta property=\"twitter:title\" content=\"" title "\">\n"
       "  <meta property=\"twitter:description\" content=\"" meta-description "\">\n"
       "  <meta property=\"twitter:image\" content=\"https://simonwasle.ch/assets/images/og-image.png\">\n"
       "  <meta property=\"twitter:site\" content=\"@eyes4theskies\">\n"
       "  <meta property=\"twitter:creator\" content=\"@eyes4theskies\">\n"
       "  <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
       "  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
       "  <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;500&display=swap\" rel=\"stylesheet\">\n"
       "  <link rel=\"stylesheet\" href=\"/assets/css/main.css\">\n"
       "  <link rel=\"icon\" href=\"/assets/images/icon32x32.png\" sizes=\"32x32\">\n"
       "  <link rel=\"icon\" href=\"/assets/images/icon192x192.png\" sizes=\"192x192\">\n"
       "  <link rel=\"apple-touch-icon\" href=\"/assets/images/icon180x180.png\">\n"
       "  <meta name=\"msapplication-TileImage\" content=\"/assets/images/icon270x270.png\">\n"
       "  <script>\n"
       "    // Prevent FOUC by setting dark class immediately\n"
       "    document.documentElement.classList.toggle(\n"
       "      'dark',\n"
       "      localStorage.theme === 'dark' ||\n"
       "        (!('theme' in localStorage) && window.matchMedia('(prefers-color-scheme: dark)').matches)\n"
       "    );\n"
       "  </script>\n"
       "  <script type=\"application/ld+json\">\n"
       "  {\n"
       "    \"@context\": \"https://schema.org\",\n"
       "    \"@type\": \"Person\",\n"
       "    \"name\": \"Simon Wasle\",\n"
       "    \"jobTitle\": \"Software Engineer\",\n"
       "    \"description\": \"Developer • Entrepreneur • Maker\",\n"
       "    \"url\": \"https://simonwasle.ch\",\n"
       "    \"address\": {\n"
       "      \"@type\": \"PostalAddress\",\n"
       "      \"streetAddress\": \"Allschwilerstrasse 50\",\n"
       "      \"addressLocality\": \"Basel\",\n"
       "      \"postalCode\": \"4055\",\n"
       "      \"addressCountry\": \"CH\"\n"
       "    },\n"
       "    \"sameAs\": [\n"
       "      \"https://github.com/das-monki\",\n"
       "      \"https://twitter.com/eyes4theskies\",\n"
       "      \"https://www.linkedin.com/in/simon-wasle-00ab56376\"\n"
       "    ]\n"
       "  }\n"
       "  </script>\n"
       "</head>\n"
       "<body class=\"font-sans\">\n"
       "  <div id=\"root\">" content "</div>\n"
       (when script-src
         (str "  <script src=\"" script-src "\" defer></script>\n"))
       "</body>\n"
       "</html>"))

(defn render-page [page-component {:keys [title script-src meta-description]}]
  (let [content (dom.server/render-to-string ($ page-component))]
    (html-template {:title title
                    :content content
                    :script-src script-src
                    :meta-description meta-description})))

(defn render-home []
  (render-page home/home-page
               {:title "Simon Wasle"
                :script-src "/assets/js/home.js"
                :meta-description "developer, entrepreneur, maker"}))
