(ns aoc-2022.day07-test
  (:require
   [aoc-2022.day07 :as day07]
   [clojure.test :refer [deftest is testing]]))

(def input ["$ cd /"
            "$ ls"
            "dir a"
            "14848514 b.txt"
            "8504156 c.dat"
            "dir d"
            "$ cd a"
            "$ ls"
            "dir e"
            "29116 f"
            "2557 g"
            "62596 h.lst"
            "$ cd e"
            "$ ls"
            "584 i"
            "$ cd .."
            "$ cd .."
            "$ cd d"
            "$ ls"
            "4060174 j"
            "8033020 d.log"
            "5626152 d.ext"
            "7214296 k"])

(deftest test-part1
  (testing "Test Part1"
    (is (= (day07/part1 input) 95437))))

(deftest test-part2
  (testing "Test Part2"
    (is (= (day07/part2 input) 24933642))))
