(ns wrapping-paper.core
  (:require [wrapping-paper.present :as present]))

(defn -main
  "Prints the total square feet of wrapping paper needed for packages resource"
  [& args]
  (println
   "Total wrapping paper needed:"
   (present/total-wrapping-paper (present/read-presents "packages.txt"))
   "sq ft"))
