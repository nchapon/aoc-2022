(ns aoc-2022.day04
  (:require
   [aoc-2022.utils :as utils]
   [clojure.set :as set]
   [clojure.string :as str]))

(defn pair-values
  "Return all pair values from a string"
  [s]
  (map (fn [s] (Integer/parseInt s))
       (str/split s #",|-")))

(defn parse-pair
  "Parse a pair from string s"
  [s]
  (let [pair-values (pair-values s)]
    (map (fn [v] (zipmap [:begin :end] v))
         (partition 2 pair-values))))

(defn- process-range-fully-contains-the-other?
  "Returns true if assignemnt pair range fully overlap"
  [pair]
  (let [first-elfe (first pair)
        second-elfe (second pair)]
    (or
     (and
      (>= (:begin first-elfe) (:begin second-elfe))
      (<= (:end first-elfe) (:end second-elfe)))
     (and
      (>= (:begin second-elfe) (:begin first-elfe))
      (<= (:end second-elfe) (:end first-elfe))))))

(def range-fully-contain-the-other?
  (comp process-range-fully-contains-the-other? parse-pair))

(defn part1
  "Count assignement pairs fully contained"
  [lines]
  (->> lines
       (map range-fully-contain-the-other?)
       (filter true?)
       count))

(defn range-overlap?
  [pair]
  (let [values (pair-values pair)
        first-elfe (into #{} (range (nth values 0) (+ (nth values 1) 1)))
        second-elfe (into #{} (range (nth values 2) (+ (nth values 3) 1)))]
    (if (not-empty (set/intersection first-elfe second-elfe))
      true
      false)))

(defn part2
  "Part 2 : count all range overlap"
  [pairs]
  (->> pairs
       (map range-overlap?)
       (filter true?)
       count))

(comment

  (def pairs ["2-4,6-8"
              "2-3,4-5"
              "5-7,7-9"
              "2-8,3-7"
              "6-6,4-6"
              "2-6,4-8"])

  (parse-pair "2-4,6-8")
  ;; => ({:begin 2, :end 4} {:begin 6, :end 8})

  (process-range-fully-contains-the-other?
   [{:begin 2, :end 4}
    {:begin 6, :end 8}]);; => false

  (range-fully-contain-the-other? "2-4,6-8");; => false
  (range-fully-contain-the-other? "2-3,4-5");; => false
  (range-fully-contain-the-other? "5-7,7-9");; => false
  (range-fully-contain-the-other? "2-8,3-7");; => true
  (range-fully-contain-the-other? "6-6,4-6");; => true
  (range-fully-contain-the-other? "2-6,4-8");; => false

  (part1 pairs)
  ;; => 2

  (part1 (utils/get-puzzle-input "day04"))
;; => 576

;; part2
  (range-overlap? "2-4,6-8");; => false
  (range-overlap? "2-3,4-5");; => false
  (range-overlap? "5-7,7-9");; => true
  (range-overlap? "2-8,3-7");; => true
  (range-overlap? "6-6,4-6");; => true
  (range-overlap? "2-6,4-8");; => true

  (part2 pairs)
  ;; => 4

  (part2 (utils/get-puzzle-input "day04"))
  ;; => 905
  )










