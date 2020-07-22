(defn collatz
    "Computes a Collatz sequence for a given number"
    [n]
    (defn collatz-helper
        [n acc]
        (if (= n 1)
            (conj acc 1)
            (if (even? n)
                (recur (/ n 2) (conj acc n))
                (recur (+ (* 3 n) 1) (conj acc n)))))
    (collatz-helper n []))

(defn max-collatz-length
    "Returns longest Collatz sequence of numbers between 1 and n"
    [n]
    (apply max (map count (map collatz (range 1 (+ n 1))))))

(max-collatz-length 1000000)
