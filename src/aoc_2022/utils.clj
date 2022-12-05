(ns aoc-2022.utils
  (:require
   [clojure.string :as string]))

(defn get-puzzle-input
  "Get Puzzle Input for Day as string"
  [day]
  (string/split-lines
   (slurp (format "input/%s.txt" day))))














