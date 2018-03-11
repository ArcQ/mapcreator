(ns mapcreator.builder.ruleDict-test
  (:require [clojure.test :refer :all]
            [ mapcreator.builder.ruleDict :as ruleDict :refer :all]
            ))

(def testGameMapOne [[000][000][000]])
(def testGameMapTwo [[010][100][010]])
(def startingLocOne {:x 2 :y 2})
(def middleLoc {:x 1 :y 1})

(deftest getSuroundings-test
  (testing "returns type vector"
    (is (vector? (ruleDict/getSuroundings testGameMapOne startingLocOne) )))
  (testing "return surrounding tiles accurately"
    (is (= {:up 1 :down 1 :left 1 :right 0} (ruleDict/getSuroundings testGameMapOne startingLocOne)) ))
  )
