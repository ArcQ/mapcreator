(ns mapcreator.core-test
  (:require [clojure.test :refer :all]
            [mapcreator.core :as core :refer :all]))

(def testGameMapOne [[000] [000] [000]])
(def testGameMapTwo [[010] [100] [010]])
(def startingLocOne {:x 2 :y 2})
(def middleLoc {:x 1 :y 1})

(deftest getSurroundings-test
  (testing "has start and stop functions"
    (is (and
         (fn? (:stop (core/system)))
         (fn? (:start (core/system)))))))
