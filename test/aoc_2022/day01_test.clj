(ns aoc-2022.day01-test
  (:require
   [aoc-2022.day01 :refer [get-calories-by-elfe get-most-calories
                           get-top3-most-calories]]
   [clojure.test :as t :refer [deftest is testing]]))

(def input
  ["1000"
   "2000"
   "3000"
   ""
   "4000"
   ""
   "5000"
   "6000"
   ""
   "7000"
   "8000"
   "9000"
   ""
   "10000"])

(deftest get-calories-by-elfe-test
  (testing "Get Calories By Elfes"
    (is (= (get-calories-by-elfe input)
           '(("1000" "2000" "3000")
             ("4000")
             ("5000" "6000")
             ("7000" "8000" "9000")
             ("10000"))))))

(deftest get-most-calories-test
  (testing "Get Most Calories"
    (is (= (get-most-calories input)
           24000))))

(deftest get-top3-most-calories-test
  (testing "Get Sum Of Top 3 most calories"
    (is (= (get-top3-most-calories input) 45000))))
