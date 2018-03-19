(ns mapcreator.builder.algorithms.naive.controller
  (:require [mapcreator.builder.shared.utils :as utils]))

(load "./directions")
(load "./ruleDict")

(defn applyRules [direction]
  direction)

(defn modifyChance
  [direction gameMap curLoc]
  (def modifier (* (+ (rand-int 90) 10)
                   (->> (select-keys RULE_DICT [(keyword (:name direction)) :all])
                        (vals)
                        (flatten)
                        (into [])
                        (reduce #(* %1 (%2 {:direction direction
                                            :gameMap gameMap
                                            :curLoc curLoc})) 1))))

  (applyRules [direction])
  (update-in direction [:chance] #(* modifier %)))

(defn getDirection [gameMap curLoc]
  (def dirDict
    (into [] (map #(modifyChance % gameMap curLoc) DIRECTIONS)))
  (if (not (every? #{0} (map #(:chance %) dirDict)))
    (apply max-key :chance dirDict)))

(defn move
  [{:keys [x, y] :as curLoc} gameMap]
  (some->
   (some-> (getDirection gameMap curLoc) (:move))
   (#(% curLoc))))

(defn createPath
  [{:keys [x, y] :as initialLoc}, initialGameMap]
  initialLoc = {:x (count initialGameMap), :y (count (get initialGameMap 0))}
  (loop [iteration 0 curLoc initialLoc curGameMap initialGameMap]
    (def newGameMap (update-in curGameMap [(:y curLoc)] #(assoc % (:x curLoc) 1)))
    (def newLoc (move curLoc newGameMap))
    (if (not newLoc)
      (println "no more legal moves"))
    (if (or (not newLoc) (> iteration 100) (< (:y newLoc) 0) (< (:x newLoc) 0))
      curGameMap
      (recur (inc iteration) newLoc newGameMap))))
