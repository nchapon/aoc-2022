(ns aoc-2022.day02-test
  (:require
   [aoc-2022.day02 :refer [parse-round-2 score sum-of-scores sum-of-scores-2]]
   [clojure.test :refer [deftest is testing]]))

(def rounds
  ["A Y"
   "B X"
   "C Z"])

(deftest score-test
  (testing "Test Sores"
    (is (= (score "A Y") 8))
    (is (= (score "B X") 1))
    (is (= (score "C Z") 6))))

(deftest total-score-test
  (testing "Sum of of scores"
    (is (= (sum-of-scores rounds) 15))))

(deftest parse-round-2-test
  (testing "Testing parse-round-2")
  (is (= (parse-round-2 "A Y") [:rock :rock]))
  (is (= (parse-round-2 "B X") [:paper :rock]))
  (is (= (parse-round-2 "C Z") [:scissor :rock])))

(deftest sum-of-scores-2-test
  (testing "Testing parse-round-2")
  (is (= (sum-of-scores-2 rounds) 12)))
