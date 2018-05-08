(ns mapcreator.builder.algorithms.randomfill.controller
  (:require [mapcreator.builder.shared.utils :as utils])
  (:require [mapcreator.builder.algorithms.randomfill.check-connected :as checkConnected])
  (:require [clojure.set :as set]))

(defn findRandomZeroPoint [curHashZerosMap]
  (let [y (rand-int (count curHashZerosMap))
        x (rand-int (count (y curHashZerosMap)))
        hashId (x (y curHashZerosMap))]
    (utils/reverseHashFunc hashId)))

(defn createPath [initialLoc, finalLoc, initialGameMap]
  (loop [timesFailed 0
         curGameMap initialGameMap
         curHashZerosMap (utils/hashFuncMap initialGameMap)]
    (if (> timesFailed 100)
      curGameMap
      (let [randomLoc {:x (rand-int (:x (utils/getBounds curGameMap)))
                       :y (rand-int (:y (utils/getBounds curGameMap)))}
            nextGameMap (utils/updateElement curGameMap randomLoc 1)
            nextHashMap (utils/hashFuncMap nextGameMap)]
        (if (checkConnected/isConnected initialGameMap
                                        (utils/hashFunc initialLoc initialGameMap)
                                        (utils/hashFunc finalLoc initialGameMap))
          (recur (inc timesFailed) nextGameMap nextHashMap)
          curGameMap)))))
