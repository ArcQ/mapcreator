(ns mapcreator.builder.score_test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.score :as score :refer :all]))

(def testGameMapTwo [[0 1 0 0 1 0]
                     [1 0 0 0 1 0]
                     [0 1 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 0 0 0 1 0]
                     [0 1 0 0 1 0]])

(deftest getQuadrants_test
  (testing "get value of 4 quadrants"
    (is (= {:ne '((0 1 0) (0 1 0) (0 1 0))
            :se '((0 1 0) (0 1 0) (0 1 0))
            :sw '((0 0 0) (0 0 0) (0 1 0))
            :nw '((0 1 0) (1 0 0) (0 1 0))} (score/getQuadrants testGameMapTwo)))))

(deftest getRatio_test
  (testing "get ratio of paths to map"
    (is (= (/ 10 36) (getRatio testGameMapTwo)))))
