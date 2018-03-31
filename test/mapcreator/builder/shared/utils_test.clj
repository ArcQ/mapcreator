(ns mapcreator.builder.shared.utils-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.shared.utils :as utils]))

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

(deftest getLocVal-test
  (testing "get's the value of the map location"
    (is (= 0 (utils/getLocVal testGameMapTwo startingLocOne)))
    (is (= 0 (utils/getLocVal testGameMapOne startingLocTwo)))
    (is (= 1 (utils/getLocVal testGameMapOne locOne)))))

(deftest hashFunc-test
  (testing "given game map, hashFunc should transform each location into a unique id"
    (is (= 8 (utils/hashFunc startingLocOne testGameMapOne)))
    (is (= 4 (utils/hashFunc startingLocTwo testGameMapOne)))
    (is (= 7 (utils/hashFunc locOne testGameMapOne))))
  (testing "should give the same result given the same location"
    (is (not (= (utils/hashFunc startingLocOne testGameMapOne) (utils/hashFunc startingLocTwo testGameMapOne))))))

(deftest maxHash-test
  (testing "gets maximum index of a gameMap"
    (is (= 8 (utils/maxHash testGameMapOne)))))

(deftest hashedList-test
  (testing "given gameMap should return a list of all hashed locations"
    (is (= [0 1 2 3 4 5 6 7 8] (utils/hashFunc testGameMapOne)))))

(deftest reverseHashFunc-test
  (testing "given a hashed id and a gameMap, should be able to calculate the position at that point"
    (is (= startingLocOne (utils/reverseHashFunc 8 testGameMapOne)))
    (is (= startingLocTwo (utils/reverseHashFunc 4 testGameMapOne)))))

(deftest getLocValFromHash-test
  (testing "given a hashed id and a gameMap, should be able to calculate the value at that point"
    (is (= 0 (utils/getLocValFromHash 5 testGameMapOne)))
    (is (= 1 (utils/getLocValFromHash 1 testGameMapOne)))))