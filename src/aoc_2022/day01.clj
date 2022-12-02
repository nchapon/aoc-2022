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

(defn get-calories-by-elfe
  "Returns calories by elfe from INPUT"
  [input]
  (filter #(not (= [""] %))
          (partition-by #(.equals "" %) input)))

(defn get-most-calories
  "Get Elfe max calories"
  [calories]
  (->> calories
       get-calories-by-elfe
       (map sum-calories)
       (reduce max)))

(defn top3-calories [sum-of-calories-by-elfe]
  (apply + (take 3 (sort #(> %1 %2) sum-of-calories-by-elfe))))

(defn get-top3-most-calories
  "Get sum of top 3 max calories"
  [calories]
  (->> calories
       get-calories-by-elfe
       (map sum-calories)
       (top3-calories)))

(comment
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

  (get-calories-by-elfe input)
  ;; => (("1000" "2000" "3000")
  ;;     ("4000")
  ;;     ("5000" "6000")
  ;;     ("7000" "8000" "9000")
  ;;     ("10000"))

  (get-most-calories input)
  ;; => 24000

  ;; part1
  (get-most-calories (get-elves-calories))
;; => 75501

  ;; part 2
  (get-top3-most-calories input) ;; => 45000

  (get-top3-most-calories (get-elves-calories)) ;; => 215594
  )













