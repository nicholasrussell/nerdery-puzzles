(ns candy-cane-breeder.core
  (:require [clojure.math.combinatorics :as combo])
  (:use [clojure.pprint :only [pprint print-table]]))

(defn gen-id
  "Generates a unique id for each candy cane plant"
  []
  (str (java.util.UUID/randomUUID)))

(defn initialize-candy-cane-plants
  "Initial list of candy cane plants"
  []
  (map 
   #(assoc % :id (gen-id))
   ;; ADD CANDY CANE PLANTS HERE
   [
    {:production-value 5 :has-bred? false}
    {:production-value 3 :has-bred? false}
    {:production-value 2 :has-bred? false}
   ]))

(defn father
  "Returns the father of a generation"
  [gen]
  (first gen))

(defn mother
  "Returns the mother of a generation"
  [gen]
  (second gen))

(defn bred-child
  "Returns the bred child plant of a generation from a specific mother and father"
  [gen]
  (nth gen 2))

(defn breed-pair?
  "Determines whether the plant pair is eligible to breed"
  [plant-pair]
  (and
   (= (count plant-pair) 2)
   (not (:has-bred? (father plant-pair)))
   (not (:has-bred? (mother plant-pair)))))

(defn eligible-breeders
  "Returns list of candy cane plants that are eligible to breed"
  [candy-cane-plants]
  (remove :has-bred? candy-cane-plants))

(defn ineligible-breeders
  "Returns list of candy cane plants that are ineligible to breed"
  [candy-cane-plants]
  (filter :has-bred? candy-cane-plants))

(defn can-breed-stock?
  "Determines whether a stock can be bred"
  [stock]
  (> (count (:breeders stock)) 1))

(defn breed
  "Breeds a father and mother plant"
  [father mother]
  (let [child-production 
        (if (or (zero? (:production-value father)) (zero? (:production-value mother)))
          0
          (int
           (mod
            (Math/pow (:production-value father) (:production-value mother))
            (+ (:production-value father) (:production-value mother)))))]
    [(assoc father :has-bred? true)
     (assoc mother :has-bred? true)
     {:id (gen-id) :production-value child-production :has-bred? false}]))

(defn breed-pair
  "Breeds a pair of plants if the pair is eligible to breed"
  [pair]
  (if (breed-pair? pair)
    (breed (father pair) (mother pair))
    pair))

(defn weekly-production-value
  "Returns the weekly production value for a stock"
  [stock]
  (reduce + 0 (map :production-value (concat (:breeders stock) (:nonbreeders stock)))))

(defn first-gen
  "Converts initial stock to useable data structure"
  [initial-stock]
  (map
   (fn [breeder-permutation]
     {:breeders breeder-permutation
      :nonbreeders (ineligible-breeders initial-stock)
      :gen 1N})
   (combo/permutations (eligible-breeders initial-stock))))

(defn next-gen
  "Computes child permutations of a stock"
  [parent-gen]
  (let [breeders (:breeders parent-gen)
        nonbreeders (:nonbreeders parent-gen)
        new-gen (inc (:gen parent-gen))]
    (map
     (fn [breeder-permutation]
       {:breeders breeder-permutation
        :nonbreeders nonbreeders
        :gen new-gen})
     (combo/permutations breeders))))

(defn breed-x
  "Breeds a stock and associates child permutations that are ready for breeding"
  [stock]
  (let [new-stock (mapcat breed-pair (partition 2 2 nil (:breeders stock)))]
    (assoc
     stock
     :children
     (next-gen
      {:breeders (eligible-breeders new-stock)
       :nonbreeders (concat (:nonbreeders stock) (ineligible-breeders new-stock))
       :gen (:gen stock)}))))

(defn breed-stock
  "Breeds a stock and any children stock that may be bred"
  [stock]
  (if (can-breed-stock? stock)
    (let [bred-stock (breed-x stock)]
      (assoc
       bred-stock
       :children
       (map
        breed-stock
        (:children bred-stock))))
    stock))

(defn max-production-value-stock
  "Finds the max production value stock out of all bred stock"
  [stocks]
  (let [default {:breeders [] :nonbreeders []}]
    (if (empty? stocks)
      default
      (reduce
       (fn [max-stock stock]
         (if (empty? stock)
           max-stock
           (let [stock-production-value (weekly-production-value stock)
                 max-child-stock (max-production-value-stock (:children stock))
                 max-production-value (weekly-production-value max-stock)]
             (if (> stock-production-value (weekly-production-value max-child-stock))
               (if (> stock-production-value max-production-value)
                 stock
                 max-stock)
               (if (> (weekly-production-value max-child-stock) max-production-value)
                 max-child-stock
                 max-stock)))))
       default
       stocks))))

(defn pretty-print-stock
  "Pretty prints a candy cane plant stock"
  [stock]
  (println "Candy Cane Plant stock:")
  (print-table
   (sort
    #(compare (:production-value %2) (:production-value %1))
    (map
     #(dissoc % :id)
     (concat (:breeders stock) (:nonbreeders stock)))))
  (prn)
  (println "Weekly production value: " (weekly-production-value stock))
  (println "Generation: " (:gen stock)))

(defn -main
  "Prints the maximum weekly production value for permutations of bred candy cane plant stock"
  [& args]
  (let [stock (initialize-candy-cane-plants)]
    (time
      (pretty-print-stock
       (max-production-value-stock
        (map
         breed-stock
         (first-gen stock)))))))

