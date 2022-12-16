(ns aoc-2022.day05-test
  (:require
   [aoc-2022.day05 :as day05]
   [clojure.test :refer [deftest is testing]]))

(def example
  ["    [D]    "
   "[N] [C]    "
   "[Z] [M] [P]"
   " 1   2   3" ""
   "move 1 from 2 to 1"
   "move 3 from 1 to 3"
   "move 2 from 2 to 1"
   "move 1 from 1 to 2"])

(deftest test-parse-stacks
  (testing "Parse Stacks"
    (is (= (day05/parse-stacks example)
           ['() '("N" "Z") '("D" "C" "M") '("P")]))))

(deftest test-parse-moves
  (testing "Parse moves"
    (is (= (day05/parse-moves example)
           '([1 2 1]
             [3 1 3]
             [2 2 1]
             [1 1 2])))))

(def stacks ['()
             '("N" "Z")
             '("D" "C" "M")
             '("P")])

(deftest test-move-crates
  (testing "Move Crates"
    (is (= (day05/move-crates stacks [1 2 1])
           ['()
            '("D" "N" "Z")
            '("C" "M")
            '("P")]))))

(deftest test-part1
  (testing "Testing part1"
    (is (= (day05/part1 example) "CMZ"))))

;; Part 2

(def stacks-2 ['()
               '("D" "N" "Z")
               '("C" "M")
               '("P")])

(deftest test-move-crates-2
  (testing "Move Crates fror Part 2"
    (is (= (day05/move-crates stacks [1 2 1])
           ['()
            '("D" "N" "Z")
            '("C" "M")
            '("P")]))
    (is (= (day05/move-crates-2 stacks-2 [3 1 3])
           ['()
            '()
            '("C" "M")
            '("D" "N" "Z" "P")]))))

(deftest test-part2
  (testing "Testing part2"
    (is (= (day05/part2 example) "MCD"))))
