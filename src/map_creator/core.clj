(ns map-creator.core
  (:gen-class))

(def DIRECTIONS [
                 {
                  :name "up" :chance 1.1 :move { :x 0 :y -1 }}
                 {
                  :name "left" :chance 1 :move {:x -1 :y 0}}
                 ])

(defn blankGameMap
  [{:keys [x y] :as dimensions}]
  (def vectorOf #(vec (replicate %1 %2)))
  (vectorOf y (vectorOf x 0)))

(defn modifyChance
  [chance]
  (def modifier (rand-int 100))
  (* modifier chance))

(defn getDirection
  []
  (def dirDict 
    (into [] (map #(update-in % [:chance] 
                              modifyChance) DIRECTIONS)))
  (apply max-key :chance dirDict))

(defn move
  [{:keys [x, y] :as curLoc}]
  (def direction (get (getDirection) :name))
  (println curLoc)
  (case direction
    "up" (update curLoc :y dec)
    "left" (update curLoc :x dec)
    "default")
  )

(defn createPath
  [{:keys [x, y] :as initialLoc}, initialGameMap]
  (loop [iteration 0 curLoc initialLoc curGameMap initialGameMap]
    (def newGameMap (update-in curGameMap [(get curLoc :y)] #(assoc % (get curLoc :x) 1)))
    (def newLoc (move curLoc))
    (if (or (< (get newLoc :y) 0) (< (get newLoc :x) 0))
      curGameMap
      (recur (inc iteration) newLoc newGameMap)))
  )

(defn -main
  [& args]
  (def initialGameMap (blankGameMap {:x 10 :y 10}))
  (run! println (createPath {:x 9 :y 9} initialGameMap)))

