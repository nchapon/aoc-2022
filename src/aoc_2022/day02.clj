(ns aoc-2022.day02
  "AOC 2022 Day 02"
  (:require
   [aoc-2022.utils :refer [get-puzzle-input]]
   [clojure.string :as str]))

(def shape-points {:rock 1
                   :paper 2
                   :scissor 3})

(def outcome-points {:lost 0
                     :draw 3
                     :won 6})

(def col1->shapes {"A" :rock
                   "B" :paper
                   "C" :scissor})

(def col2->shapes {"X" :rock
                   "Y" :paper
                   "Z" :scissor})

(defn parse-round
  "Parse round"
  [round]
  (let [[col1 col2] (str/split round #" ")]
    [(get col1->shapes col1)
     (get col2->shapes col2)]))

(defn outcome
  "Round Outcome"
  [round]
  (cond
    (= round [:rock :paper]) :won
    (= round [:scissor :rock]) :won
    (= round [:paper :scissor]) :won
    (= (first round) (second round)) :draw
    :else :lost))

(defn calculate-score
  "Score of a round"
  [round]
  (let [outcome (outcome round)]
    (+
     (get outcome-points outcome)
     (get shape-points (second round)))))

(def score (comp calculate-score parse-round))

(defn sum-of-scores
  "Total score"
  [rounds]
  (->> rounds
       (map score)
       (reduce +)))

(defn- looser-shape-from
  "Return the looser shape from c1 shape"
  [col1]
  (case col1
    :rock :scissor
    :paper :rock
    :scissor :paper))

(defn- need-to-win
  "Return the winner shape from c1 shape"
  [col1]
  (case col1
    :scissor :rock
    :paper :scissor
    :rock :paper))

(defn- get-col2-shape
  "Get col2 shape from its original value s2 (X,Y or Z) and c1 shape."
  [s2 col1]
  (case s2
    "X" (looser-shape-from col1)
    "Y" col1
    "Z" (need-to-win col1)))

(defn parse-round-2
  "Parse round from string"
  [s]
  (let [[s1 s2] (str/split s #" ")
        c1 (get col1->shapes s1)]
    [c1
     (get-col2-shape s2 c1)]))

(def score-2 (comp calculate-score parse-round-2))

(defn sum-of-scores-2
  "Sum of scores for part2"
  [strings]
  (->> strings
       (map score-2)
       (reduce +)))

(comment

  (def puzzle
    ["A Y"
     "B X"
     "C Z"])

  (outcome [:rock :paper]);; => :won
  (outcome [:scissor :paper]);; => :lost

  (sum-of-scores puzzle) ;; => 15

  ;; part1
  (sum-of-scores (get-puzzle-input "day02")) ;; => 10595

  ;; part2
  (parse-round-2 "A Y") ;; => [:rock :rock]
  (parse-round-2 "B X");; => [:paper :rock]
  (parse-round-2 "C Z");; => [:scissor :rock]

  (sum-of-scores-2 puzzle);; => 12

  (sum-of-scores-2 (get-puzzle-input "day02")) ;; => 9541 
  )

















