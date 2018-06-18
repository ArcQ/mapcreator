(ns mapcreator.builder.algorithms.randomfill.add-obstacle)

(defn randomPoint [buffer gameMap]
  {:y (rand-int (- (count gameMap) (:y buffer)))
   :x (rand-int (- (count (gameMap 0)) (:x buffer)))})

(defn makeObstacleVector [{xDim :x yDim :y :as dimensions}]
  (map
   (fn [yVal] (map #(vector %1 %2) (range xDim) (replicate 3 yVal)))
   (range yDim)))

(defn addObstacleToGameMap [gameMap {xDim :x yDim :y :as dimensions} {xPoint :x yPoint :y :as point}]
  (map-indexed
   (fn [ydx row]
     (map-indexed
      (fn [xdx ele]  (if (and
                          (>= ydx yPoint)
                          (< ydx (+ yPoint yDim))
                          (>= xdx xPoint)
                          (< xdx (+ xPoint xDim)))
                       1
                       0))
      row))
   gameMap))

(defn addObstacle [gameMap {xDim :x yDim :y :as dimensions}]
  (doall (->> gameMap
              (randomPoint dimensions)
              (addObstacleToGameMap gameMap dimensions))))

(def testGameMapTwo [[0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]])

(print (addObstacle testGameMapTwo {:x 2 :y 3}))
