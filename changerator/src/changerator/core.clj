(ns changerator.core
  (:require [changerator.coin :as coin])
  (:gen-class))

(defn -main
  "Prints the number of coins needed to make change for given dollar amount"
  [& args]
  (when (not (= (count args) 1))
    (throw (IllegalArgumentException. "Provide a dollar amount")))
  (println (coin/make-change (first args))))
