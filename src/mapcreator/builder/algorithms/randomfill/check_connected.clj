(ns mapcreator.builder.algorithms.randomfill.check-connected
  (:require [mapcreator.builder.shared.utils :as utils])
  (:require [clojure.set :as set]))

(load "../directions")

(defn isMapValZero [hashId]
  (= 0 (utils/getLocValFromHash hashId)))

(defn getRight [hashId]
  (inc hashId))

(defn getBelow [hashId gameMap]
  (+ hashId (:x (utils/getBounds gameMap))))

(defn notFirstColumn [hashId gameMap]
  (not= (mod hashId (:x (utils/getBounds gameMap))) 0))

(defn notFirstColumn [hashId gameMap]
  (not= (mod hashId (:x (utils/getBounds gameMap))) 0))

(defn getAdjacent [hashId gameMap]
  (if (not= (utils/getLocValFromHash hashId gameMap) 0)
    nil
    (->> [(getRight hashId)
          (getBelow hashId gameMap)]
         ((fn [[rightId belowId]]
            (concat
              []
              (if (and
                    (notFirstColumn rightId gameMap)
                    (= (utils/getLocValFromHash rightId gameMap) 0))
                [#{hashId rightId}]
                nil)
              (if (and
                    (<=  belowId (utils/maxHash gameMap))
                    (= (utils/getLocValFromHash belowId gameMap) 0))
                [#{hashId belowId}]
                nil)))))))

(defn getAdjacentSets [gameMap]
  (->> gameMap
       (utils/hashList)
       (map #(getAdjacent % gameMap))
       (flatten)
       (remove nil?)
       (vec)))

(defn hasCommon [hashSetOne hashSetTwo]
  (>
   (+ (count hashSetOne) (count hashSetTwo))
   (count (set/union hashSetOne hashSetTwo))))

(defn unionConnectedSet [newHashIdList nextHash]
  (loop [i 0]
    (cond
      (>= i (count newHashIdList)) (conj newHashIdList nextHash)
      (hasCommon nextHash (newHashIdList i)) (assoc newHashIdList i (set/union nextHash (newHashIdList i)))
      :else (recur (inc i)))))

(defn unionConnectedSets [adjacentSets]
  (reduce #(unionConnectedSet %1 %2) [(adjacentSets 0)] (drop 1 adjacentSets)))

(defn isConnected [gameMap, hashedIdOne, hashedIdTwo]
  (def connectedSets (-> gameMap
                         getAdjacentSets
                         unionConnectedSets))
  (->> connectedSets
       (reduce #(or %1
                   (and (contains? %2 hashedIdOne)
                     (contains? %2 hashedIdTwo))) 
               false)))
