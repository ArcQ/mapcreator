(ns mapcreator.builder.algorithms.randomfill.controller
  (:require [mapcreator.builder.shared.utils :as utils]))

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

(defn unionConnectedSets [adjacentSets])

(defn isConnected [connectedSets])
