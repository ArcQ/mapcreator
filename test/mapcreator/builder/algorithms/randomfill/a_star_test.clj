(ns mapcreator.builder.algorithms.randomfill.check-connected-test
  (:require [clojure.test :refer :all]
            [mapcreator.builder.algorithms.randomfill.check-connected :as checkConnected :refer :all]))

(def testGameMapOne [[0 0 0 0 0 0 0]
                     [0 0 0 1 0 0 0]
                     [0 0 0 1 0 0 0]
                     [0 0 0 1 0 0 0]
                     [0 0 0 0 0 0 0]])

(def path ([1 2] [2 2] [2 3] [2 4] [3 4] [4 4] [4 3] [4 2] [5 2]))

(deftest isConnected-test
  (testing "given the sets found in unionConnectedSets, should return true if connected"
    (is (= path (search maze1 [1 2] [5 2])))))
