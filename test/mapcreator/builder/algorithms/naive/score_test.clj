(ns mapcreator.builder.score_test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.score :as score :refer :all]))

(def testGameMapTwo [[0 1 0 0 1 0]
                     [1 0 0 0 1 0]
                     [0 1 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 1 0 0 1 0]])

(def testGameMapThree [[0 0 0 0 1 0]
                       [0 0 0 0 1 0]
                       [0 0 0 0 1 0]
                       [0 0 0 0 1 0]
                       [0 1 0 0 1 0]
                       [0 1 0 0 1 0]])

(def testGameMapFour [[1 1 0 0 1 0]
                      [0 1 0 0 1 0]
                      [0 1 0 0 1 0]
                      [0 1 0 0 1 0]
                      [0 1 0 0 1 0]
                      [1 1 1 1 1 1]])

(deftest getQuadrants_test
  (testing "get value of 4 quadrants"
    (is (= {:ne '((0 1 0) (0 1 0) (0 1 0))
            :se '((0 1 0) (0 1 0) (0 1 0))
            :sw '((0 0 0) (0 0 0) (0 1 0))
            :nw '((0 1 0) (1 0 0) (0 1 0))} (score/getQuadrants testGameMapTwo)))))

(deftest getRatio_test
  (testing "get ratio of paths to map"
    (is (= (/ 10 36) (getRatio testGameMapTwo)))))

(deftest noLowRatio_test
  (testing "should work for "
    (is (= false (noLowRatio testGameMapTwo 0.5))))
  (testing "shouldn't work"
    (is (= true (noLowRatio testGameMapFour 0.3)))))

(deftest noEmptyQuadrants_test
  (testing "if less than req paths per quadrant, then should fail"
    (is (= false (noEmptyQuadrants testGameMapThree 0.3))))
  (testing "if each quadrant meets the req paths, then should pass"
    (is (= true (noEmptyQuadrants testGameMapFour 0.3)))))

(deftest passCriteria_test
  (testing "should pass if passes all criteria"
    (is (= false (passCriteria testGameMapTwo {:noEmptyQuadrants 0.3 :noLowRatio 0.4}))))
  (testing "should fail if fails even one criteria"
    (is (= true (passCriteria testGameMapFour {:noEmptyQuadrants 0.3 :noLowRatio 0.4})))))
