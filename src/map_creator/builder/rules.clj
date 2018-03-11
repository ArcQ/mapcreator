(ns map-creator.builder.rules
  (:gen-class)
  )

(load "./directions")

(defn getSuroundings [gameMap curLoc] 
  (map DIRECTIONS #())
  []
  )

(defn bounds [{:keys [direction gameMap curLoc]}]
  (def gameMapSize {:x (count (get gameMap 0)) :y (count gameMap)})
  (def nextLoc ((:move direction) curLoc))
  (if (or
        (> (:x nextLoc) (- (:x gameMapSize) 1)) 
        (> (:y nextLoc) (- (:y gameMapSize) 1))
        (< (:x nextLoc) 0) 
        (< (:x nextLoc) 0) 
        )
    0
    1)
  )

(defn noBack [{:keys [direction gameMap curLoc]}]
  (def nextLoc ((get direction :move) curLoc))
  (if (= (get-in gameMap [(:y nextLoc) (:x nextLoc)]) 1) 
    0
    1)
  )

(defn noTouchingPaths [{:keys [direction gameMap curLoc]}]
  (def nextLoc ((:move direction) curLoc))
  (if (= (get-in gameMap [(:y nextLoc) (:x nextLoc)]) 1) 
    0
    1)
  )

(def RULES_DICT { 
                 :up []
                 :left []
                 :right []
                 :down []
                 :all [bounds noBack]
                 })
