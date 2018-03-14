(ns mapcreator.builder.ruleDict-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.ruleDict :as ruleDict :refer :all]))

(def testGameMapOne [[0 0 0] [0 0 0] [0 0 0]])
(def testGameMapTwo [[0 1 0] [1 0 0] [0 1 0]])
(def startingLocOne {:x 2 :y 2})
(def middleLoc {:x 1 :y 1})

(deftest getSurroundings-test
  (testing "returns type map"
    (is (map? (ruleDict/getSurroundings testGameMapOne startingLocOne))))
  (testing "return surrounding tiles accurately"
    (is (= {:up 1 :down 1 :left 1 :right 0} (ruleDict/getSurroundings testGameMapTwo middleLoc)))))
