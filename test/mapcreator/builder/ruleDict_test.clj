(ns mapcreator.builder.ruleDict-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.ruleDict :as ruleDict :refer :all]))

(def testGameMapOne [[0 1 0]
                     [1 0 0]
                     [0 1 0]])

(def testGameMapTwo [[0 1 0 0 1 0]
                     [1 0 0 0 1 0]
                     [0 1 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 1 0 0 1 0]])

(def startingLocOne {:x 2 :y 2})
(def startingLocTwo {:x 1 :y 1})
(def locOne {:x 1 :y 2})
(def leftDirection
  {:name "left" :chance 1 :move #(update % :x dec)})

(def rightDirection
  {:name "right" :chance 1 :move #(update % :x inc)})

(deftest getLocVal-test
  (testing "get's the value of the map location"
    (is (= 0 (ruleDict/getLocVal testGameMapTwo startingLocOne)))
    (is (= 0 (ruleDict/getLocVal testGameMapOne startingLocTwo)))
    (is (= 1 (ruleDict/getLocVal testGameMapOne locOne)))))

(deftest getSurroundings-test
  (testing "returns type map"
    (is (map? (ruleDict/getSurroundings testGameMapOne startingLocOne))))
  (testing "return surrounding tiles accurately"
    (is (= {:up 1 :down 1 :left 1 :right 0} (ruleDict/getSurroundings testGameMapOne startingLocTwo)))))

(deftest noTouchingPaths-test
  (testing "returns type map"
    (is (ruleDict/noTouchingPaths
         {:direction leftDirection :gameMap testGameMapTwo :curLoc startingLocOne})))
  (testing "works for potential nextlocs that border the map (nil surroundings)"
    (is (= 0 (ruleDict/noTouchingPaths
              {:direction leftDirection :gameMap testGameMapTwo :curLoc locOne}))))
  (testing "0 for all directions that would cause them to be touching their suroundings"
    (is (= 0 (ruleDict/noTouchingPaths
              {:direction leftDirection :gameMap testGameMapTwo :curLoc locOne}))))
  (testing "1 for all directions that would not have touching surroundings"
    (is (= 1 (ruleDict/noTouchingPaths
              {:direction rightDirection :gameMap testGameMapTwo :curLoc startingLocTwo})))))
