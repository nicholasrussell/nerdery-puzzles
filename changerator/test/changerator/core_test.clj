(ns changerator.core-test
  (:require [clojure.test :refer :all]
            [changerator.core :refer :all]))

(deftest main-args-test
  (testing "main checks that dollar amount is given"
    (is (thrown? IllegalArgumentException (changerator.core/-main)))))
