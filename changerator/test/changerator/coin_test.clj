(ns changerator.coin-test
  (:require [clojure.test :refer :all]
    [changerator.coin :refer :all])
  (:import [changerator.coin Coin]))

(deftest make-change-not-a-number-test
  (testing "amount is a number"
    (is (thrown? IllegalArgumentException (make-change "abcd")))))

(deftest make-change-less-than-zero-test
  (testing "amount is greater than or equal to 0"
    (is (thrown? IllegalArgumentException (make-change "-1")))))

(deftest make-change-decimal-places-leading-digit-test
  (testing "amount has no more than two decimal places"
    (is (thrown? IllegalArgumentException (make-change "1.234")))))

(deftest make-change-decimal-places-no-leading-digit-test
  (testing "amount has no more than two decimal places"
    (is (thrown? IllegalArgumentException (make-change ".123")))))

(deftest make-change-test
  (testing "makes change for number"
    (is (= {:silver-dollar 1 :half-dollar 1 :quarter 1 :dime 1 :nickel 1 :penny 1} (make-change "1.91")))))

(deftest make-change-dollar-sign-test
  (testing "makes change for number with dollar sign"
    (is (= {:silver-dollar 1 :half-dollar 1 :quarter 1 :dime 1 :nickel 1 :penny 1} (make-change "$1.91")))))

(deftest make-change-other-currency-test
  (testing "only accepts optional USD dollar sign"
    (is (thrown? IllegalArgumentException (make-change "€1.91")))))

(deftest make-change-number-format-test
  (testing "handles valid number formats"
    (is
      (and
        (= {:silver-dollar 1 :half-dollar 0 :quarter 0 :dime 0 :nickel 0 :penny 0} (make-change "1"))
        (= {:silver-dollar 1 :half-dollar 0 :quarter 0 :dime 0 :nickel 0 :penny 0} (make-change "1."))
        (= {:silver-dollar 1 :half-dollar 0 :quarter 0 :dime 2 :nickel 0 :penny 0} (make-change "1.2"))
        (= {:silver-dollar 0 :half-dollar 0 :quarter 0 :dime 2 :nickel 0 :penny 0} (make-change ".2"))
        (= {:silver-dollar 0 :half-dollar 0 :quarter 0 :dime 2 :nickel 0 :penny 3} (make-change ".23"))))))

(deftest make-change-zero-test
  (testing "makes change for $0.00"
    (is (= {:silver-dollar 0 :half-dollar 0 :quarter 0 :dime 0 :nickel 0 :penny 0} (make-change "0")))))

(deftest make-change-max-int-test
  (testing "makes change for ${MAX_INTEGER}.99"
    (is (= {:silver-dollar Integer/MAX_VALUE :half-dollar 1 :quarter 1 :dime 2 :nickel 0 :penny 4} (make-change (str Integer/MAX_VALUE ".99"))))))

(deftest make-change-overflow-test
  (testing "throws exception for > Integer/MAX_VALUE numbers"
    (is (thrown? IllegalArgumentException (make-change (str Integer/MAX_VALUE "0"))))))

(deftest make-change-helper-other-coins-test
  (testing "making change given other coins"
    (is (= {:blork 3 :foosbar 4 :quantilaan 2} (make-change-helper 6.34M [(Coin. :blork 2.0M) (Coin. :foosbar 0.08M) (Coin. :quantilaan 0.01M)])))))
