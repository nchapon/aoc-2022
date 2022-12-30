(ns aoc-2022.day07
  (:require
   [aoc-2022.utils :as utils]
   [clojure.string :as string]))

(defn- compute-path [current-path dir]
  (cond
    (empty? current-path) "/"
    (= "/" current-path) (str "/" dir)
    :else (str current-path "/" dir)))

(defn parent-path
  "Return parent pat from current path"
  [current-path]
  (if (= "/" current-path)
    nil
    (let [[_ parent] (re-find #"(/\S+)*/\S+" current-path)]
      (if (empty? parent) "/" parent))))

(defn parse-dirname
  "Find Dirname from string"
  [current-path s]
  (let [[_ dir] (re-find #"dir (.*)" s)]
    (compute-path current-path dir)))

(defn parse-size
  "Parse size from string"
  [s]
  (let [[_ size] (re-find #"(\d+) .*" s)]
    (Integer/parseInt size)))

(defn parse-cd
  "Find Dirname from string"
  [s]
  (let [[_ dir] (re-find #"cd (.*)" s)]
    dir))

(defn update-size
  "Update size directory and parents"
  [dirs command current-path]
  (let [size (parse-size command)]
    (loop [temp-dirs dirs
           path current-path]
      (if-not (empty? path)
        (recur (update temp-dirs path + size) (parent-path path))
        temp-dirs))))

(defn add-directory
  "Add directory"
  [dirs command current-path]
  (println "Add directory" command)
  (assoc dirs (parse-dirname current-path command) 0))

(defn update-current-path [command path]
  (cond
    (= "$ cd .." command) (parent-path path)
    (string/starts-with? command "$ cd ") (compute-path path (parse-cd command))
    :else path))

(defn parse-command
  "Parse "
  [dirs command current-path]
  (println dirs)
  (cond
    (string/starts-with? command "dir") (add-directory dirs command current-path)
    (re-seq #"(\d+)" command) (update-size dirs command current-path)
    :else  dirs))

(defn parse-commands
  "Parse commands"
  [lines]
  (loop [dirs {"/" 0}
         cmd ""
         current-path ""
         commands lines]
    (if-not (empty? commands)
      (recur (parse-command dirs cmd current-path)
             (first commands)
             (update-current-path cmd current-path)
             (rest commands))
      (parse-command dirs cmd current-path))))

(defn part1
  "What is the sum of the total sizes of those directories?"
  [input]
  (->> input
       parse-commands
       (filter (fn [[k v]] (< v 100000)))
       vals
       (apply +)))

(def ^:private disk-size
  70000000)

(def ^:private size-needed
  30000000)

(defn part2
  "What is the total size of the smallest directory to delete ?"
  [input]
  (let [dirs (parse-commands input)
        total-used (get dirs "/")
        unused-disk (- disk-size total-used)]
    (loop [smallest-size 0
           size-of-dirs-to-delete (rest (sort (vals dirs)))]
      (if (> (+ smallest-size unused-disk) size-needed)
        smallest-size
        (recur (first size-of-dirs-to-delete) (rest size-of-dirs-to-delete))))))

(comment

  (def example ["$ cd /"
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

  (parse-size "4060174 j")
  ;; => 4060174

  (parse-cd "$ cd xyz")
  ;; => "xyz"

  (parse-dirname "/" "dir xys")
  ;; => "/xys"

  (update-current-path "$ cd /" "")
  ;; => "/"
  (update-current-path "$ cd a" "/")
  ;; => "/a"
  (update-current-path "$ cd e" "/a")
  ;; => "/a/e"
  (update-current-path "$ cd .." "/a/e")
  ;; => "/a"
  (update-current-path "$ cd .." "/a")
  ;; => "/"

  (parent-path "/a")
  ;; => "/"

  (parent-path "/")

  (parse-commands example)
  ;; => {"/" 48381165, "/a" 94853, "/d" 24933642, "/a/e" 584}

  (part1 example)
;; => 95437

  (part1 (utils/get-puzzle-input "day07"))
  ;; => 1141028

  (part2 example)
  ;; => 24933642

  (part2 (utils/get-puzzle-input "day07"))
  ;; => 8278005
  )












