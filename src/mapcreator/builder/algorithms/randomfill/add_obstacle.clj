(ns mapcreator.builder.algorithms.randomfill.add-obstacle
  (:require [mapcreator.builder.shared.utils :as utils]))

(defn randomPoint
  "offset is for pushing the random point so that it will be within a certain quadrant"
  ([offset dimensions gameMap]
   (randomPoint offset {:x 0 :y 0} dimensions gameMap))
  ([offset buffer dimensions gameMap]

   (letfn [(getPointDim [k dim]
             (-> dim
                 (- (k offset) (k buffer) (k dimensions))
                 (rand-int)
                 (+ (k offset))))]
     {:y (getPointDim :y (count gameMap))
      :x (getPointDim :x (count (gameMap 0)))})))

(defn makeObstacleVector [{xDim :x yDim :y :as dimensions}]
  (map
   (fn [yVal] (map #(vector %1 %2) (range xDim) (replicate 3 yVal)))
   (range yDim)))

(defn addObstacleToGameMap [gameMap {xDim :x yDim :y :as dimensions} {xPoint :x yPoint :y :as point}]
  (vec (map-indexed
        (fn [ydx row]
          (vec (map-indexed
                (fn [xdx ele]  (if (and
                                    (>= ydx yPoint)
                                    (< ydx (+ yPoint yDim))
                                    (>= xdx xPoint)
                                    (< xdx (+ xPoint xDim)))
                                 1
                                 (utils/getLocVal gameMap {:x xdx :y ydx})))
                row)))
        gameMap)))

(def testGameMapTwo [[0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]])

(defn getTestGameMapThree []
  (vec (repeat 28
               (vec
                (repeat 18 0)))))

(run! prn
      (let [dimensions {:x 3 :y 3}
            gameMap (getTestGameMapThree)]
        (doall (reduce
                #(->> %1
                      (randomPoint
                       (:offset %2)
                       (:buffer %2)
                       dimensions)
                      (addObstacleToGameMap %1 dimensions))
                gameMap
                (utils/getBufferOffsetZone gameMap {:x 4 :y 4})))))
