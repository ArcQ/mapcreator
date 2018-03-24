(ns mapcreator.builder.algorithms.naive.controller-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.algorithms.naive.controller :as controller :refer :all]))

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

(deftest getSurroundings-test
  (testing "returns type map"
    (is (map? (controller/getSurroundings testGameMapOne startingLocOne))))
  (testing "return surrounding tiles accurately"
    (is (= {:up 1 :down 1 :left 1 :right 0} (controller/getSurroundings testGameMapOne startingLocTwo)))))

(deftest noTouchingPaths-test
  (testing "returns type map"
    (is (controller/noTouchingPaths
         {:direction leftDirection :gameMap testGameMapTwo :curLoc startingLocOne})))
  (testing "works for potential nextlocs that border the map (nil surroundings)"
    (is (= 0 (controller/noTouchingPaths
              {:direction leftDirection :gameMap testGameMapTwo :curLoc locOne}))))
  (testing "0 for all directions that would cause them to be touching their suroundings"
    (is (= 0 (controller/noTouchingPaths
              {:direction leftDirection :gameMap testGameMapTwo :curLoc locOne}))))
  (testing "1 for all directions that would not have touching surroundings"
    (is (= 1 (controller/noTouchingPaths
              {:direction rightDirection :gameMap testGameMapTwo :curLoc startingLocTwo})))))
