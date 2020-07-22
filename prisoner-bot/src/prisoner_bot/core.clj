(ns prisoner-bot.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def SILENT "silent")
(def CONFESS "confess")
(def JVM "jvm")
(def MAX-TURNS 100)

(def FILE-NAME "nrussell-prisoner-bot-data")
(def FILE (str "./" FILE-NAME))

(defn partner-name
  [args]
  (nth args 0 nil))

(defn partner-discipline 
  [args]
  (nth args 1 nil))

(defn partner-response 
  [args]
  (nth args 2 nil))

(defn player-response
  [args]
  (nth args 3 nil))

(defn mapify-args
  [args]
  {:partner-name (partner-name args)
   :partner-discipline (partner-discipline args)
   :partner-response (partner-response args)
   :player-response (player-response args)})

(defn jvm?
  [turn-data]
  (= (:partner-discipline turn-data) JVM))

(defn last-response-silent?
  [turn-data]
  (= (:partner-response turn-data) SILENT))

(defn last-response-confess?
  [turn-data]
  (= (:partner-response turn-data) CONFESS))

(defn random-confess
  []
  (if (> (rand-int 100) 96)
    SILENT
    CONFESS))

(defn default-turn-data
  []
  {:turn 0
   :partner-response nil
   :player-response nil
   :silent-count 0
   :confess-count 0})

(defn read-turn-data
  [file]
  (if (.exists (io/as-file file))
    (read-string (slurp file))
    (default-turn-data)))

(defn current-turn
  [turn-data]
  (+ (:turn turn-data) 1))

(defn write-turn-data
  [turn-data file]
  (spit file turn-data :append false))

(defn last-turn?
  [turn-data]
  (> (current-turn turn-data) (- MAX-TURNS 1)))

(defn update-turn-data
  [turn-data file]
  (write-turn-data
   (let [old-turn-data (read-turn-data file)]
     (if (and
          (not (nil? (:partner-name old-turn-data)))
          (not (= (:partner-name old-turn-data) (:partner-name turn-data))))
       (apply assoc turn-data (into [] (flatten (seq (default-turn-data)))))
       (let [turn-data (assoc turn-data :turn (current-turn turn-data))
             silent-count (if (nil? (:silent-count turn-data)) 0 (:silent-count turn-data))
             confess-count (if (nil? (:confess-count turn-data)) 0 (:confess-count turn-data))]
         (if (last-response-silent? turn-data)
           (assoc turn-data :silent-count (+ silent-count 1))
           (if (last-response-confess? turn-data)
             (assoc turn-data :confess-count (+ confess-count 1))
             turn-data)))))
   file))

(defn -main
  [& args]
  (let [turn-data (merge (read-turn-data FILE) (mapify-args args))
        response (cond
                   (nil? (:partner-response turn-data)) SILENT
                   (last-turn? turn-data) CONFESS
                   ;(jvm? args) SILENT
                   (last-response-silent? turn-data) SILENT
                   (last-response-confess? turn-data) (random-confess)
                   :else SILENT)]
    (println response)
    (update-turn-data turn-data FILE)))

