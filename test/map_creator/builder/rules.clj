;; (ns map-creator.builder.rules-test
;;   (:require [clojure.test :refer :all]
;;             [ map-creator.builder.rules :as rules :refer :all]
;;             ))
;;
;; (def testGameMapOne [[000][000][000]])
;; (def testGameMapTwo [[010][100][010]])
;; (def startingLocOne {:x 2 :y 2})
;; (def middleLoc {:x 1 :y 1})
;;
;; (deftest getSuroundings-test
;;   (testing "returns type vector"
;;     (is (vector? (core/getSuroundings testGameMapOne startingLocOne) )))
;;   (testing "returns surroundings"
;;     (is (= {:up 1 :down 1 :left 1 :right 0} (core/getSuroundings testGameMapOne startingLocOne)) ))
;;   )
