(ns mapcreator.builder.algorithms.randomfill.check-connected-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.algorithms.randomfill.check-connected :as checkConnected :refer :all]))

(def testGameMapOne [[0 1 0]
                     [1 0 0]
                     [0 1 0]])

(def testGameMapTwo [[0 1 0 0 1 0]
                     [1 0 0 0 1 0]
                     [0 1 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 1 0 0 1 0]])

(def testGameMapThree [[0 0 1 0]
                       [0 1 1 1]
                       [0 1 0 0]])

(deftest notFirstColumn-test
  (testing "returns false if in firstColumn of game map"
    (is (= false (checkConnected/notFirstColumn 0 testGameMapOne)))
    (is (= false (checkConnected/notFirstColumn 4 testGameMapThree))))
  (testing "returns true if not in firstColumn of game map"
    (is (= true (checkConnected/notFirstColumn 1 testGameMapThree)))))

(deftest getAdjacentSets-test
  (testing "returns all adjacent points that are adjacent"
    (is (= [#{0 1} #{0 4} #{4 8} #{10 11}] (checkConnected/getAdjacentSets testGameMapThree)))))

(deftest unionConnectedSets-test
  (testing "should group getAdjacentSets into groups of points that are connected together"
    (is (= [#{0 1 4 8} #{10 11}] (checkConnected/unionConnectedSets [#{0 1} #{0 4} #{4 8} #{10 11}])))))

(deftest isConnected-test
  (testing "given the sets found in unionConnectedSets, should return true if connected"
    (is (= true (checkConnected/isConnected testGameMapThree 4 8))))
  (testing "given the sets found in unionConnectedSets, should return false if one of them was a wall"
    (is (= false (checkConnected/isConnected testGameMapThree 4 5))))
  (testing "given the sets found in unionConnectedSets, should return false if not connected"
    (is (= false (checkConnected/isConnected testGameMapThree 0 11)))))
