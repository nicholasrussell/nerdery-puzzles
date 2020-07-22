(ns lightparens.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  (q/cursor-image (q/load-image "resources/lambda.png"))

  {:lightsaber-color '(47 249 35)
   :slider-height (- (q/height) 350)
   :base-image (q/load-image "resources/lightparensbase.png")
   :xkcd-image (q/load-image "resources/lisp_cycles.png")
   :done-animating? false})

(defn update-state [state]
  {:lightsaber-color (:lightsaber-color state)
   :slider-height (if (<= (:slider-height state) 0)
                    0
                    (- (:slider-height state) 5))
   :base-image (:base-image state)
   :xkcd-image (:xkcd-image state)
   :done-animating? (<= (:slider-height state) 0)})

(defn draw-state [state]
  (q/background 240)
  (q/stroke-weight 5)
  (apply q/stroke (:lightsaber-color state))
  (apply q/fill (:lightsaber-color state))
  (q/ellipse (/ (q/width) 2) (/ (q/height) 2) 300 600)
  (q/fill 240)
  (q/ellipse (/ (q/width) 2) (/ (q/height) 2) 250 550)
  (q/stroke-weight 0)
  (q/stroke 240 240 240)
  (q/rect (- (/ (q/width) 2) (/ 100 2)) 0 100 (q/height))
  (q/image (:base-image state) 0 (- (q/height) 350))
  (q/rect 0 0 (q/width) (:slider-height state))
  (if (:done-animating? state)
    (q/image (:xkcd-image state) (- (/ (q/width) 2) (/ 252 2)) 0)))

(q/defsketch lightparens
  :title "Light Parens / Lisp Cycles"
  :size [600 800]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])

(defn -main
  [& args]
  "Do nothing")

;; Generated the background image
;(defn draw-background [state]
;  (q/stroke-weight 5)
;  (q/stroke 0)
;  (q/fill 0)
;  (q/ellipse (/ (q/width) 2) (/ (q/height) 2) 300 600)
;  (q/fill 240)
;  (q/ellipse (/ (q/width) 2) (/ (q/height) 2) 250 550)
;  (q/fill 240)
;  (q/ellipse (/ (q/width) 2) (/ (q/height) 2) 250 550)
;  (q/stroke-weight 0)
;  (q/stroke 240 240 240)
;  (q/rect (- (/ (q/width) 2) (/ 100 2)) 0 100 (q/height))
;  (q/rect 0 0 (q/width) (- (q/height) 350)))
