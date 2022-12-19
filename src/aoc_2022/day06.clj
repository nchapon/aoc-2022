(ns aoc-2022.day06
  (:require
   [aoc-2022.utils :as utils]))

(defn first-start-marker
  ""
  [nb-chars input]
  (loop [marker 0]
    (if (not (= nb-chars (count (set (subs input marker (+ marker nb-chars))))))
      (recur
       (inc marker))
      (+ marker nb-chars))))

(def first-start-of-packet-marker (partial first-start-marker 4))
(def first-start-of-message-marker (partial first-start-marker 14))

(defn part1
  "How many characters need to be processed
  before the first start-of-packet marker is detected?"
  [input]
  (first-start-of-packet-marker (first input)))

(defn part2
  "How many characters need to be processed before the
  first start-of-message marker is detected?"
  [input]
  (first-start-of-message-marker (first input)))

(comment

  (first-start-of-packet-marker "mjqjpqmgbljsphdztnvjfqwrcgsmlb")
;; => 7
  (first-start-of-packet-marker "bvwbjplbgvbhsrlpgdmjqwftvncz")
;; => 5
  (first-start-of-packet-marker "nppdvjthqldpwncqszvftbrmjlhg")
;; => 6
  (first-start-of-packet-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
;; => 10

  (first-start-of-packet-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
  ;; => 11

  (part1 (utils/get-puzzle-input "day06"))
  ;; => 1912

  ;; Part 2
  (first-start-of-message-marker "mjqjpqmgbljsphdztnvjfqwrcgsmlb") ;; => 19
  (first-start-of-message-marker "bvwbjplbgvbhsrlpgdmjqwftvncz") ;; => 23
  (first-start-of-message-marker "nppdvjthqldpwncqszvftbrmjlhg");; => 23 
  (first-start-of-message-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg");; => 29
  (first-start-of-message-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");; => 26

  (part2 (utils/get-puzzle-input "day06"))
  ;; => 2122
  )





