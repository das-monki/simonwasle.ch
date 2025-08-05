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
       "  <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
       "  <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
       "  <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap\" rel=\"stylesheet\">\n"
       "  <link rel=\"stylesheet\" href=\"/assets/css/main.css\">\n"
       "  <style>\n"
       "    body { font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; }\n"
       "    @keyframes wave {\n"
       "      0%, 100% { transform: translateY(0px); }\n"
       "      50% { transform: translateY(-8px); }\n"
       "    }\n"
       "    .wave-1 { animation: wave 3s ease-in-out infinite; }\n"
       "    .wave-2 { animation: wave 3s ease-in-out infinite -0.5s; }\n"
       "    .wave-3 { animation: wave 3s ease-in-out infinite -1s; }\n"
       "    @media (max-width: 768px) {\n"
       "      .landscape-svg {\n"
       "        --sun-stroke: 4;\n"
       "        --sun-ray-stroke: 3;\n"
       "        --wave-stroke-1: 5;\n"
       "        --wave-stroke-2: 4;\n"
       "        --wave-stroke-3: 3;\n"
       "        --boat-stroke: 5;\n"
       "        --boat-mast-stroke: 4;\n"
       "        --boat-sail-stroke: 3;\n"
       "        --sun-radius: 22;\n"
       "        --boat-width: 100;\n"
       "        height: 8rem;\n"
       "      }\n"
       "      .wave-1, .wave-2, .wave-3 {\n"
       "        animation: wave 3s ease-in-out infinite;\n"
       "      }\n"
       "      .wave-2 {\n"
       "        animation-delay: -0.5s;\n"
       "      }\n"
       "      .wave-3 {\n"
       "        animation-delay: -1s;\n"
       "      }\n"
       "      @keyframes wave {\n"
       "        0%, 100% { transform: translateY(0px); }\n"
       "        50% { transform: translateY(-12px); }\n"
       "      }\n"
       "    }\n"
       "  </style>\n"
       "</head>\n"
       "<body>\n"
       "  <div id=\"root\">" content "</div>\n"
       (when script-src
         (str "\n  <script src=\"/assets/js/shared.js\"></script>\n"
              "  <script src=\"" script-src "\"></script>\n"))
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
                :meta-description "Simon Wasle's personal website"}))

