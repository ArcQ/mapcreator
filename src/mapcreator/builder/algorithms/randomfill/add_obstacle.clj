(ns mapcreator.builder.algorithms.randomfill.add-obstacle)

(defn randomPoint
  "offset is for pushing the random point so that it will be within a certain quadrant"
  [offset dimensions gameMap]
  (randomPoint offset dimensions dimensions gameMap)
  [offset buffer dimensions gameMap]

  (letfn [(getPointDim [k dim]
            (-> dim
                (- (k buffer) (k offset))
                (+ (k buffer))
                (rand-int)))]
    {:y (getPointDim :y (count gameMap))
     :x (getPointDim :x (count (gameMap 0)))}))

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
  (doall (->> (randomPoint {:x 2 :y 2} dimensions gameMap)
              (addObstacleToGameMap gameMap dimensions))))

(def testGameMapTwo [[0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]
                     [0 0 0 0 0 0 0]])

(defn getTestGameMapThree []
  (doall (vec (repeat 27 (vec (doall (repeat 18 0)))))))

;; (run! prn (addObstacle testGameMapTwo {:x 2 :y 3}))

(run! prn (addObstacle (getTestGameMapThree) {:x 3 :y 3}))
