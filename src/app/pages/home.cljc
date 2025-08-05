(ns app.pages.home
  (:require [uix.core :refer [defui $]]))

(defui header-section []
  ($ :header
    {:class "mb-10 md:mb-16 max-w-prose mx-auto"}
    ($ :h1
      {:class "text-3xl mb-2 text-gray-900"}
      "Simon Wasle")
    ($ :p
      {:class "text-md text-gray-900"}
      "developer • entrepreneur • speaker")))

(defui landscape-graphic []
  ($ :div
    {:class "mb-10 md:mb-16 max-w-prose mx-auto"}
    ($ :svg
      {:class "landscape-svg w-fill"
       :viewBox "0 0 800 128"
       :preserveAspectRatio "xMidYMid meet"
       :fill "none"
       :stroke "currentColor"
       :stroke-linecap "round"
       :style {:--sun-stroke "2.5"
               :--sun-ray-stroke "2"
               :--wave-stroke-1 "3"
               :--wave-stroke-2 "2.5"
               :--wave-stroke-3 "2"
               :--boat-stroke "3"
               :--boat-mast-stroke "2.5"
               :--boat-sail-stroke "2"
               :--sun-radius "16"
               :--boat-width "80"}}
      ;; Sun (moved higher up)
      ($ :circle
        {:cx "650"
         :cy "30"
         :r "16"
         :stroke-width "var(--sun-stroke)"})
      ;; Sun rays (with consistent 3px gap from circle)
      ($ :g
        {:stroke-width "var(--sun-ray-stroke)"}
        ($ :line {:x1 "650" :y1 "7" :x2 "650" :y2 "2"})
        ($ :line {:x1 "674" :y1 "17" :x2 "680" :y2 "13"})
        ($ :line {:x1 "685" :y1 "30" :x2 "693" :y2 "30"})
        ($ :line {:x1 "674" :y1 "43" :x2 "680" :y2 "49"})
        ($ :line {:x1 "650" :y1 "53" :x2 "650" :y2 "58"})
        ($ :line {:x1 "626" :y1 "43" :x2 "620" :y2 "49"})
        ($ :line {:x1 "615" :y1 "30" :x2 "607" :y2 "30"})
        ($ :line {:x1 "626" :y1 "17" :x2 "620" :y2 "13"}))
      ;; Animated waves (positioned lower, extending full width)
      ($ :g
        {:class "wave-1"}
        ($ :path
          {:d "M0 85 Q100 70 200 85 T400 85 T600 85 T800 85"
           :stroke-width "var(--wave-stroke-1)"
           :fill "none"}))
      ($ :g
        {:class "wave-2"}
        ($ :path
          {:d "M0 92 Q150 64 300 92 T600 92 T800 92"
           :stroke-width "var(--wave-stroke-2)"
           :fill "none"}))
      ($ :g
        {:class "wave-3"}
        ($ :path
          {:d "M0 99 Q80 84 160 99 T320 99 T480 99 T640 99 T800 99"
           :stroke-width "var(--wave-stroke-3)"
           :fill "none"}))
      ;; Boat (animated with waves)
      ($ :g
        {:class "wave-2"}
        ;; Boat hull (larger, more boat-like)
        ($ :path
          {:d "M110 69 L110 75 Q150 79 190 75 L190 69 L110 69"
           :stroke-width "var(--boat-stroke)"
           :fill "none"})
        ;; Boat mast
        ($ :line
          {:x1 "150"
           :y1 "69"
           :x2 "150"
           :y2 "42"
           :stroke-width "var(--boat-mast-stroke)"})
        ;; Main sail (large triangle)
        ($ :path
          {:d "M150 44 L150 62 L175 62 Z"
           :stroke-width "var(--boat-sail-stroke)"
           :fill "none"})
        ;; Jib sail (front triangle)
        ($ :path
          {:d "M150 47 L125 62 L150 62 Z"
           :stroke-width "var(--boat-sail-stroke)"
           :fill "none"})
        ;; Small flag at top
        ($ :path
          {:d "M150 42 L160 44 L150 47"
           :stroke-width "var(--boat-sail-stroke)"
           :fill "none"})))))

(defui who-section []
  ($ :section
    {:class "mb-10 md:mb-16 max-w-prose mx-auto"}
    ($ :h2
      {:class "text-2xl mb-6 text-gray-900"}
      "Who I am")
    ($ :div
      {:class "prose prose-lg text-gray-700 leading-relaxed"}
      ($ :p
        {:class "mb-4"}
        "I'm a passionate software engineer with over a decade of experience building scalable systems and leading development teams. My expertise spans full-stack development, system architecture, and modern web technologies.")
      ($ :p
        {:class "mb-4"}
        "I believe in writing clean, maintainable code and fostering collaborative engineering cultures. When I'm not coding, you'll find me exploring new technologies, contributing to open source projects, or sharing knowledge with the developer community.")
      ($ :p
        "Currently based in Switzerland, I work with teams worldwide to solve complex technical challenges and deliver impactful software solutions."))))

(defui what-i-offer-section []
  ($ :section
    {:class "mb-10 md:mb-16 max-w-prose mx-auto"}
    ($ :h2
      {:class "text-2xl mb-6 text-gray-900"}
      "What I offer")
    ($ :div
      {:class "grid gap-8 md:grid-cols-2"}
      ($ :div
        {:class ""}
        ($ :h3
          {:class "text-lg mb-3 text-gray-900"}
          "Technical Leadership")
        ($ :p
          {:class "text-gray-700 leading-relaxed"}
          "Guiding engineering teams through complex projects, establishing best practices, and mentoring developers to reach their full potential."))
      ($ :div
        {:class ""}
        ($ :h3
          {:class "text-lg mb-3 text-gray-900"}
          "Full-Stack Development")
        ($ :p
          {:class "text-gray-700 leading-relaxed"}
          "Building end-to-end solutions using modern technologies including React, Node.js, Clojure, and cloud platforms."))
      ($ :div
        {:class ""}
        ($ :h3
          {:class "text-lg mb-3 text-gray-900"}
          "System Architecture")
        ($ :p
          {:class "text-gray-700 leading-relaxed"}
          "Designing scalable, maintainable systems that grow with your business needs and handle real-world complexity."))
      ($ :div
        {:class ""}
        ($ :h3
          {:class "text-lg mb-3 text-gray-900"}
          "Consulting & Training")
        ($ :p
          {:class "text-gray-700 leading-relaxed"}
          "Helping organizations adopt modern development practices, improve code quality, and accelerate delivery.")))))

(defui contact-section []
  ($ :section
    {:class "mb-10 md:mb-16 max-w-prose mx-auto"}
    ($ :h2
      {:class "text-xl font-semibold mb-6 text-gray-900"}
      "Get in touch")
    ($ :div
      {:class "text-gray-700 leading-relaxed"}
      ($ :div
        {:class "flex gap-4 mb-3"}
        ($ :a
          {:href "https://github.com/das-monki"
           :class "text-gray-600 hover:text-gray-900 transition-colors"
           :target "_blank"
           :rel "noopener noreferrer"}
          ($ :svg
            {:class "w-7 h-7"
             :fill "none"
             :stroke "currentColor"
             :stroke-width "2.0"
             :viewBox "0 0 24 24"}
            ($ :path
              {:stroke-linecap "round"
               :stroke-linejoin "round"
               :d "M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"})))
        ($ :a
          {:href "https://twitter.com/eyes4theskies"
           :class "text-gray-600 hover:text-gray-900 transition-colors"
           :target "_blank"
           :rel "noopener noreferrer"}
          ($ :svg
            {:class "w-7 h-7"
             :fill "none"
             :stroke "currentColor"
             :stroke-width "2.0"
             :viewBox "0 0 24 24"}
            ($ :path
              {:stroke-linecap "round"
               :stroke-linejoin "round"
               :d "M23 3a10.9 10.9 0 01-3.14 1.53 4.48 4.48 0 00-7.86 3v1A10.66 10.66 0 013 4s-4 9 5 13a11.64 11.64 0 01-7 2c9 5 20 0 20-11.5a4.5 4.5 0 00-.08-.83A7.72 7.72 0 0023 3z"})))
        ($ :a
          {:href "https://www.linkedin.com/in/simon-wasle-00ab56376"
           :class "text-gray-600 hover:text-gray-900 transition-colors"
           :target "_blank"
           :rel "noopener noreferrer"}
          ($ :svg
            {:class "w-7 h-7"
             :fill "none"
             :stroke "currentColor"
             :stroke-width "1.8"
             :viewBox "0 0 24 24"}
            ($ :path
              {:stroke-linecap "round"
               :stroke-linejoin "round"
               :d "M16 8a6 6 0 016 6v7h-4v-7a2 2 0 00-2-2 2 2 0 00-2 2v7h-4v-7a6 6 0 016-6zM2 9h4v12H2z"})
            ($ :circle
              {:cx "4"
               :cy "4"
               :r "2"
               :stroke-linecap "round"
               :stroke-linejoin "round"}))))
      ($ :div
        {:class "text-gray-700"}
        ($ :p
          {:class "mb-1"}
          ($ :a
            {:href "mailto:contact@simonwasle.ch"
             :class "text-blue-600 hover:text-blue-800"}
            "contact@simonwasle.ch"))
        ($ :p
          {:class "mb-1"}
          "Allschwilerstrasse 50")
        ($ :p
          {:class "mb-1"}
          "4055 Basel")
        ($ :p
          "Switzerland")))))

(defui footer []
  ($ :footer
    {:class "mt-10 md:mt-16 max-w-prose mx-auto"}
    ($ :div
      {:class "text-center"}
      ($ :p
        {:class "text-xs font-light text-gray-500"}
        "No cookies for you! This page respects your data and privacy. <3 Stay healthy, stay strong."))))

(defui home-page []
  ($ :div
     {:class "min-h-screen bg-white"}
     ($ :div
        {:class "max-w-4xl mx-auto px-6 py-12"}
        ($ header-section)
        ($ landscape-graphic)
        ($ who-section)
        ($ what-i-offer-section)
        ($ contact-section)
        ($ footer))))
