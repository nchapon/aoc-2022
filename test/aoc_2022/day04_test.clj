(ns aoc-2022.day04-test
  (:require
   [aoc-2022.day04 :as day04]
   [clojure.test :refer [deftest is testing]]))

(def pairs ["2-4,6-8"
            "2-3,4-5"
            "5-7,7-9"
            "2-8,3-7"
            "6-6,4-6"
            "2-6,4-8"])

(deftest test-part1
  (testing "Part 1 Test : counting fully contained pairs")
  (is (= (day04/part1 pairs) 2)))

(deftest test-parse-pair
  (is (= (day04/parse-pair "2-4,6-8")
         [{:begin 2, :end 4} {:begin 6, :end 8}])))

(deftest test-one-range-fully-contain-the-other
  (testing "One Range Fully Contain The Other")
  (is (= (day04/range-fully-contain-the-other? "2-4,6-8") false))
  (is (= (day04/range-fully-contain-the-other? "2-3,4-5") false))
  (is (= (day04/range-fully-contain-the-other? "5-7,7-9") false))
  (is (= (day04/range-fully-contain-the-other? "2-8,3-7") true))
  (is (= (day04/range-fully-contain-the-other? "6-6,4-6") true))
  (is (= (day04/range-fully-contain-the-other? "2-6,4-8") false)))

(deftest test-range-overlap?
  (testing "Testing range overlap")
  (is (=  (day04/range-overlap? "2-4,6-8") false))
  (is (=  (day04/range-overlap? "2-3,4-5") false))
  (is (=  (day04/range-overlap? "5-7,7-9") true))
  (is (=  (day04/range-overlap? "2-8,3-7") true))
  (is (=  (day04/range-overlap? "6-6,4-6") true))
  (is (=  (day04/range-overlap? "2-6,4-8") true)))

(deftest test-part2
  (testing "Part 2 : counting all range overlap")
  (is (= (day04/part2 pairs) 4)))
