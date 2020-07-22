(ns wrapping-paper.present
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defprotocol PresentAttributes
  "Common present attributes"
  (areas [this] "Returns the side areas of a present")
  (surface-area [this] "Returns the surface area of a present")
  (excess [this] "Returns the excess (area of smallest side) or a present"))

(defrecord Present [length width height]
  PresentAttributes
  (areas
    [this]
    (list (* length width) (* width height) (* length height)))
  (surface-area
    [this]
    (reduce + 0 (map #(* 2 %) (areas this))))
  (excess
    [this]
    (apply min (areas this))))

(defn wrapping-paper-for-present
  "Calculates the wrapping paper needed (surface area + excess) for a present"
  [present]
  (+ (surface-area present) (excess present)))

(defn total-wrapping-paper
  "Sums the wrapping paper needed for each present"
  [presents]
  (reduce + 0 (map wrapping-paper-for-present presents)))

(defn read-presents
  "Reads a list of LxWxH presents from a resource file"
  [resource]
  (let [input-data (slurp (io/file (io/resource resource)))]
    (map
     #(let [[l w h] (map read-string (str/split % #"x"))]
        (Present. l w h))
     (str/split input-data #"\r?\n"))))
