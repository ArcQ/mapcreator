(ns mapcreator.builder.shared.utils-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.shared.utils :as utils]))

(def testGameMapOne [[0 1 0]
                     [1 0 0]
                     [0 1 0]])

(def testGameMapOneModified [[0 1 0]
                             [1 1 0]
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

(def hashFuncMapRes `((0 1 2 3 4 5) (6 7 8 9 10 11) (12 13 14 15 16 17) (18 19 20 21 22 23) (24 25 26 27 28 29) (30 31 32 33 34 35)))
(deftest hashFuncMap-test
  (testing "given bounds, should be ab le to create hash map"
    (is (= hashFuncMapRes (utils/hashFuncMap testGameMapTwo)))))

(deftest maxHash-test
  (testing "gets maximum index of a gameMap"
    (is (= 8 (utils/maxHash testGameMapOne)))))

(deftest hashedList-test
  (testing "given gameMap should return a list of all hashed locations"
    (is (= [0 1 2 3 4 5 6 7 8] (utils/hashList testGameMapOne)))))

(deftest reverseHashFunc-test
  (testing "given a hashed id and a gameMap, should be able to calculate the position at that point"
    (is (= startingLocOne (utils/reverseHashFunc 8 testGameMapOne)))
    (is (= startingLocTwo (utils/reverseHashFunc 4 testGameMapOne)))))

(deftest getLocValFromHash-test
  (testing "given a hashed id and a gameMap, should be able to calculate the value at that point"
    (is (= 0 (utils/getLocValFromHash 5 testGameMapOne)))
    (is (= 1 (utils/getLocValFromHash 1 testGameMapOne)))))

(deftest updateElement-test
  (testing "given a position and a newVal update correctly"
    (is (= testGameMapOneModified (utils/updateElement testGameMapOne {:x 1 :y 1} 1)))))
