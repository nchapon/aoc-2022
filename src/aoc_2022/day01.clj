(ns aoc-2022.day01
  "FIXME: my new org.corfield.new/scratch project.")

(defn exec
  "Invoke me with clojure -X aoc-2022.aoc-2022/exec"
  [opts]
  (println "exec with" opts))

(defn -main
  "Invoke me with clojure -M -m aoc-2022.aoc-2022"
  [& args]
  (println "-main with" args))

(def calories ["1" "2" "3" "" "1" "2"])

(defn get-calories
  "Get Calories"
  []
  (clojure.string/split-lines (slurp "input/day01.txt")))











