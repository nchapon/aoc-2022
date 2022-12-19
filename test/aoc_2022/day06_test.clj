(ns aoc-2022.day06-test
  (:require
   [aoc-2022.day06 :as day06]
   [clojure.test :refer [deftest is testing]]))

(def example ["mjqjpqmgbljsphdztnvjfqwrcgsmlb"])

(deftest test-first-start-of-packet-marker
  (testing "First Start Of Packet Marker")
  (is (= (day06/first-start-of-packet-marker "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7))
  (is (=  (day06/first-start-of-packet-marker "bvwbjplbgvbhsrlpgdmjqwftvncz") 5))
  (is (=  (day06/first-start-of-packet-marker "nppdvjthqldpwncqszvftbrmjlhg") 6))
  (is (=  (day06/first-start-of-packet-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10))
  (is (=  (day06/first-start-of-packet-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11)))

(deftest test-first-start-of-message-marker
  (testing "First Start Of Message Marker")
  (is (=  (day06/first-start-of-message-marker "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19))
  (is (=  (day06/first-start-of-message-marker "bvwbjplbgvbhsrlpgdmjqwftvncz") 23))
  (is (=  (day06/first-start-of-message-marker "nppdvjthqldpwncqszvftbrmjlhg") 23))
  (is (=  (day06/first-start-of-message-marker "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29))
  (is (=  (day06/first-start-of-message-marker "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26)))






