(ns aoc-2022.day03-test
  (:require
   [aoc-2022.day03 :refer [convert-to-priority find-group-type find-item-type
                           parse-items priority-group priority-items
                           sum-of-priorities-group sum-of-priority-items]]
   [clojure.test :refer [deftest is testing]]))

(def rucksacks ["vJrwpWtwJgWrhcsFMMfFFhFp"
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                "PmmdzqPrVvPwwTWBwg"
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                "ttgJtRGJQctTZtZT"
                "CrZsJsPPZsGzwwsLwLmpwMDw"])
;; part1
(deftest parse-items-test
  (testing "Parsing item test"
    (is (= (parse-items "vJrwpWtwJgWrhcsFMMfFFhFp") ["vJrwpWtwJgWr" "hcsFMMfFFhFp"]))))

(deftest find-item-type-test
  (is (= (find-item-type "vJrwpWtwJgWr" "hcsFMMfFFhFp") \p)))

(deftest convert-to-priority-test
  (testing "Converting char priority tests"
    (is (=  (convert-to-priority \a)  1))
    (is (=  (convert-to-priority \p)  16))
    (is (=  (convert-to-priority \z)  26))
    (is (=  (convert-to-priority \A)  27))
    (is (=  (convert-to-priority \Z)  52))))

(deftest priority-items-test
  (testing "Get the priority from the items string")
  (is (= (priority-items "vJrwpWtwJgWrhcsFMMfFFhFp") 16)))

(deftest sum-of-priority-items-test
  (testing "Sum Of Priority Items for Rucksacks")
  (is (= (sum-of-priority-items rucksacks)  157)))

;; part2
(def first-group ["vJrwpWtwJgWrhcsFMMfFFhFp"
                  "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                  "PmmdzqPrVvPwwTWBwg"])

(def second-group ["wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                   "ttgJtRGJQctTZtZT"
                   "CrZsJsPPZsGzwwsLwLmpwMDw"])

(deftest find-group-type-test
  (testing "Find Group Type  tests")
  (is (= (apply find-group-type first-group) \r))
  (is (= (apply find-group-type second-group) \Z)))

(deftest priority-group-test
  (testing "Priority group test")
  (is (=  (priority-group first-group) 18))
  (is (=  (priority-group second-group) 52)))

(deftest sum-of-priorities-group-test
  (testing "Sum Of Priorities Group")
  (is (= (sum-of-priorities-group rucksacks) 70)))

;; Result part 2


