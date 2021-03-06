(ns mapcreator.builder.algorithms.randomfill.controller
  (:require [mapcreator.builder.shared.utils :as utils])
  (:require [mapcreator.builder.algorithms.randomfill.check-connected :as checkConnected])
  (:require [clojure.set :as set]))

(defn addInterestingDestination [gameMap])

(defn findRandomZeroPoint [curHashZerosMap]
  (let [y (rand-int (count curHashZerosMap))
        x (rand-int (count (y curHashZerosMap)))
        hashId (x (y curHashZerosMap))]
    (utils/reverseHashFunc hashId)))

(defn getRandomLoc [gameMap]
  (let [hashMapZeroes (utils/hashFuncMapZeroes gameMap)
        y (rand-int (count hashMapZeroes))
        x (rand-int (count (nth hashMapZeroes y)))]
    (-> (nth hashMapZeroes y)
        (nth x)
        (utils/reverseHashFunc hashMapZeroes))))

(defn randomFill [initialLoc, finalLoc, initialGameMap]
  (loop [timesFailed 0
         curGameMap initialGameMap]
    (if (> timesFailed 3)
      curGameMap
      (let [randomLoc (getRandomLoc curGameMap)
            nextGameMap (utils/updateElement curGameMap randomLoc 1)]
        (if (checkConnected/isConnected nextGameMap
                                        (utils/hashFunc initialLoc initialGameMap)
                                        (utils/hashFunc finalLoc initialGameMap))
          (recur 0 nextGameMap)
          (recur (inc timesFailed) curGameMap))))))

(defn createPath [initialLoc, finalLoc, initialGameMap]
  (randomFill initialLoc finalLoc initialGameMap))
