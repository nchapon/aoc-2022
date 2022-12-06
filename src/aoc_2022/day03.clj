(ns aoc-2022.day03
  "AOC 2022 Day 3"
  (:require
   [aoc-2022.utils :refer [get-puzzle-input]]))

(defn parse-items
  "Parse two items from string"
  [s]
  (let [idx (/ (.length s) 2)]
    [(subs s 0 idx) (subs s idx)]))

(defn find-item-type
  "Returns item-type as char from the two items"
  [s1 s2]
  (first
   (for [x (seq (char-array s1))
         y (seq (char-array s2))
         :when (= x y)]
     x)))

(defn convert-to-priority
  "Convert Char to priority"
  [c]
  (let [val (int c)]
    (if (> val 96) ;;lowercase
      (- val 96)
      (- val 38))))

(defn priority-items
  "Returns the priority of the items "
  [s]
  (let [[item1 item2] (parse-items s)]
    (convert-to-priority
     (find-item-type item1 item2))))

(defn sum-of-priority-items
  "Returns the sum of the priority items"
  [lines]
  (reduce + (map priority-items lines)))

(defn find-group-type
  "Returns item-type as char from the two items"
  [s1 s2 s3]
  (first
   (for [x (seq (char-array s1))
         y (seq (char-array s2))
         z (seq (char-array s3))
         :when (= x y z)]
     x)))

(defn priority-group
  "Get priority item by group"
  [group]
  (let [[s1 s2 s3] group]
    (convert-to-priority (find-group-type s1 s2 s3))))

(defn sum-of-priorities-group
  "Sum of priorities group"
  [lines]
  (reduce +
          (map priority-group (partition 3 lines))))

(comment
  (def rucksacks ["vJrwpWtwJgWrhcsFMMfFFhFp"
                  "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                  "PmmdzqPrVvPwwTWBwg"
                  "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                  "ttgJtRGJQctTZtZT"
                  "CrZsJsPPZsGzwwsLwLmpwMDw"])

  (parse-items "vJrwpWtwJgWrhcsFMMfFFhFp") ;; => ["vJrwpWtwJgWr" "hcsFMMfFFhFp"]

  (find-item-type "vJrwpWtwJgWr" "hcsFMMfFFhFp");; => \p

  (convert-to-priority \a) ;; => 1
  (convert-to-priority \p) ;; => 16
  (convert-to-priority \z) ;; => 26
  (convert-to-priority \A) ;; => 27
  (convert-to-priority \Z) ;; => 52

  (priority-items "vJrwpWtwJgWrhcsFMMfFFhFp");; => 16

  (sum-of-priority-items rucksacks);; => 157

  (def puzzle (get-puzzle-input "day03"))

  ;; Result part 1
  (sum-of-priority-items puzzle);; => 7845

  ;; part2
  (def first-group ["vJrwpWtwJgWrhcsFMMfFFhFp"
                    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                    "PmmdzqPrVvPwwTWBwg"])

  (def second-group ["wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                     "ttgJtRGJQctTZtZT"
                     "CrZsJsPPZsGzwwsLwLmpwMDw"])

  (apply find-group-type first-group);; => \r
  (apply find-group-type second-group);; => \Z

  (priority-group first-group);; => 18
  (priority-group second-group);; => 52 

  (sum-of-priorities-group rucksacks);; => 70
  ;; Result part 2
  (sum-of-priorities-group puzzle);; => 2790
  )














