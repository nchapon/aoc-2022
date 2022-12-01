(ns aoc-2022.day01
  "AOC 2022 Day 01"
  (:require
   [clojure.string :as string]))

(defn get-elves-calories
  "Get Elves Calories"
  []
  (string/split-lines (slurp "input/day01.txt")))

(defn- sum-calories
  [calories]
  (apply + (map #(Integer/parseInt %) calories)))

(defn max-calories
  "Get Elfe max calories"
  [elfes-calories]
  (reduce max (map sum-calories
                   (filter #(not (= [""] %))
                           (partition-by #(.equals "" %) elfes-calories)))))

(comment
  (def calories
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

  (max-calories calories)
;; => 24000
  (max-calories (get-elves-calories))
;; => 75501
  )
















