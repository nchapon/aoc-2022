(ns aoc-2022.day05
  (:require
   [aoc-2022.utils :as utils]))

(defn index-of-stacks-line
  "Returns index of empty line"
  [lines]
  (loop [idx 0]
    (if (re-find #"\d" (nth lines idx))
      idx
      (recur (inc idx)))))

(defn process-stack
  "Process stack column"
  [crates column]
  (let [begin (- (* column 4) 3)
        end (inc begin)]
    (apply list ; convert to list 
           (filter #(re-find #"[A-Z]" %)
                   (map (fn [cr] (subs cr begin end)) crates)))))

(defn parse-stacks
  "Parse stacks"
  [input]
  (let [idx (index-of-stacks-line input)
        crate-lines (take idx input)
        columns (re-seq #"\d" (nth input idx))]
    (into [()] ; add empty seq
          (for [column columns]
            (process-stack crate-lines (parse-long column))))))

(defn parse-moves
  "Parse Moves"
  [moves]
  (filter not-empty
          (for [move moves]
            (let [[[_ & n-from-to]] (re-seq  #"move (\d+) from (\d+) to (\d+)" move)]
              (mapv parse-long
                    n-from-to)))))

(defn move-crates
  "Move n crates from stack to stack."
  [stacks [n from to]]
  (loop [source (stacks from)
         target (stacks to)
         n n]
    (if (pos? n)
      (recur
       (pop source)
       (conj target (peek source))
       (dec n))
      (assoc stacks from source
             to target))))

(defn part1
  "What crate ends up on top of each stack?"
  [input]
  (apply str
         (map peek
              (reduce move-crates (parse-stacks input) (parse-moves input)))))

(defn move-crates-2
  "Move n crates from stack to stack and
     stay in the same order if all crates are moved from a stack."
  [stacks [n from to]]
  (loop [source (stacks from)
         temp ()
         n n]
    (if (pos? n)
      (recur
       (pop source)
       (conj temp (peek source))
       (dec n))
      (assoc stacks from source
             to (into (stacks to) temp)))))

(defn part2
  "After the rearrangement procedure completes,
   what crate ends up on top of each stack ?"
  [input]
  (apply str
         (map peek
              (reduce move-crates-2 (parse-stacks input) (parse-moves input)))))

(comment

  (def example
    ["    [D]    "
     "[N] [C]    "
     "[Z] [M] [P]"
     " 1   2   3" ""
     "move 1 from 2 to 1"
     "move 3 from 1 to 3"
     "move 2 from 2 to 1"
     "move 1 from 1 to 2"])

  (index-of-stacks-line example);; => 3
  ;; => 3

  (parse-stacks example)
  ;; => [() ("N" "Z") ("D" "C" "M") ("P")]

  (parse-moves example)
  ;; => ([1 2 1] [3 1 3] [2 2 1] [1 1 2])

  (def stacks ['() '("N" "Z") '("D" "C" "M") '("P")])

  (move-crates stacks [1 2 1])
  ;; => [() ("D" "N" "Z") ("C" "M") ("P")]

  (part1 example)
  ;; => "CMZ"

  (part1 (utils/get-puzzle-input "day05"))
  ;; => "VWLCWGSDQ"

  ;;;;;;;;
  ;; part2
  ;;;;;;;;

  (def stacks-2 ['() '("D" "N" "Z") '("C" "M") '("P")])

  (move-crates-2 stacks-2 [3 1 3])
  ;; => [() () ("C" "M") ("D" "N" "Z" "P")]

  (= (move-crates stacks [1 2 1])
     (move-crates-2 stacks [1 2 1]))
  ;; => true

  (part2 example)
  ;; => "MCD"

  (part2 (utils/get-puzzle-input "day05"))
  ;; => "TCGLQSLPW"
  )



















