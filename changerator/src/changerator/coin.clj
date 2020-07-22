(ns changerator.coin
  (:import (clojure.lang Keyword))
  (:import (java.math BigDecimal)))

;;
; Coin record
; name - Name of the coin (e.g. :quarter)
; value - Coin value (e.g. 0.25)
(defrecord Coin [^Keyword name ^BigDecimal value])
; Calculates the maximum number of a given coin that will add up to amount or less without going over
(defmulti max-coins-for-amount class)
(defmethod max-coins-for-amount Coin [coin]
  (fn [amount]
    (int (Math/floor (/ (bigdec amount) (:value coin))))))

(def COINS
  (sort ; ensure new coins added are always sorted highest value to lowest value
   #(compare (:value %2) (:value %1))
   ;; Add new coins here!
   ;; Give them a name and a bigdec (M) value
    [(Coin. :silver-dollar 1.0M)
     (Coin. :half-dollar 0.5M)
     (Coin. :quarter 0.25M)
     (Coin. :dime 0.1M)
     (Coin. :nickel 0.05M)
     (Coin. :penny 0.01M)]))

(defn make-change-helper ; should be defn-
  "Makes change given an amount and list of coins.
  Coins should be ordered in value highest to lowest."
  [amount
   coins]
  (loop [coins coins
         remaining amount
         change {}]
      (if (empty? coins)
        change
        (let [num-coins ((max-coins-for-amount (first coins)) remaining)]
          (recur
            (rest coins)
            (- remaining (* (:value (first coins)) num-coins))
            (merge change {(:name (first coins)) num-coins}))))))


(defn make-change
  "Returns the number of each coin needed to make change for amount.
  Throws IllegalArgumentException with amount is not a number, less than 0, or
  has more than two decimal places."
  [amount]
  (let [dollar-amount 
          (try
            (let [amount-without-signage (if (.startsWith amount "$") (subs amount 1) amount)]
              (bigdec amount-without-signage))
            (catch java.lang.NumberFormatException e (throw (IllegalArgumentException. "Dollar amount must be a number"))))]
    (when (< dollar-amount  0.0M)
        (throw (IllegalArgumentException. "You may only make change for dollar amounts greater than or equal to $0.00")))
    (let [decimal-index (.indexOf amount ".")]
      (when (and (>= decimal-index 0) (> (- (- (.length amount) decimal-index) 1) 2))
        (throw (IllegalArgumentException. "You may only specify up to two decimal places"))))
    (make-change-helper dollar-amount COINS)))
