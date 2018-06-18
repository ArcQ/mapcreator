(ns mapcreator.builder.algorithms.randomfill.add-obstacle-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.algorithms.randomfill.add-obstacle :as addObstacle :refer :all]))

(def testGameMapOne [[0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]])

(def obstacleMap [[0 0 0 0 0 0 0]
                  [0 1 1 0 0 0 0]
                  [0 1 1 0 0 0 0]
                  [0 1 1 0 0 0 0]
                  [0 0 0 0 0 0 0]])

(deftest makeObstacleVector-test
  (testing "given the sets found in unionConnectedSets, should return true if connected"
    (is (= obstacleMap (addObstacle/addObstacleToGameMap testGameMapOne {:x 1 :y 1} {:x 2 :y 3})))))
