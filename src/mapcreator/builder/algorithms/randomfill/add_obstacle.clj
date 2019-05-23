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

(defn makeObstacleVector [{xDim :x yDim :y :as dimensions}] (map
                                                             (fn [yVal] (map #(vector %1 %2) (range xDim) (replicate 3 yVal)))
                                                             (range yDim)))

(defn addObstacleToGameMap [gameMap {xDim :x yDim :y :as dimensions} typeCode {xPoint :x yPoint :y :as point}]
  (vec (map-indexed
        (fn [ydx row]
          (vec (map-indexed
                (fn [xdx ele]  (if (and
                                    (>= ydx yPoint)
                                    (< ydx (+ yPoint yDim))
                                    (>= xdx xPoint)
                                    (< xdx (+ xPoint xDim)))
                                 typeCode
                                 (utils/getLocVal gameMap {:x xdx :y ydx})))
                row)))
        gameMap)))

(defn randomDimension [maxDim]
  (->>
   [:x :y]
   (map #(vector % (+ 2 (rand-int maxDim))))
   (flatten)
   (apply hash-map)))

;; test code

(def testGameMapTwo [[0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]])

(defn getTestGameMapThree [x y]
  (vec (repeat y
               (vec
                (repeat x 0)))))

(defn addObstacles [gameMap typeCode maxDim zones]
  (doall (reduce
          #(let [dimensions (randomDimension maxDim)]
             (->> %1
                  (randomPoint
                   (:offset %2)
                   (:buffer %2)
                   dimensions)
                  (addObstacleToGameMap %1 dimensions typeCode)))
          gameMap
          (utils/getBufferOffsetZone gameMap zones))))

(defn createInitialMap [x y]
  (-> (addObstacles (getTestGameMapThree x y) 1 4 {:x 3 :y 3})
      (addObstacles 2 7 {:x 1 :y 1})
      (addObstacles 3 5 {:x 2 :y 2})))

;; (run! prn (-> (addObstacles (getTestGameMapThree) 1 4 {:x 3 :y 3})
;;               (addObstacles 2 7 {:x 1 :y 1})
;;               (addObstacles 3 5 {:x 2 :y 2})))

