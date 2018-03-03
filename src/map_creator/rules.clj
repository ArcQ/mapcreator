(defn bounds [{:keys [direction gameMap curLoc]}]
  (def gameMapSize {:x (count (get gameMap 0)) :y (count gameMap)})
  (def newLoc ((get direction :move) curLoc))
  (if (or
        (> (get newLoc :x) (- (get gameMapSize :x) 1)) 
        (> (get newLoc :y) (- (get gameMapSize :y) 1))
        (< (get newLoc :x) 0) 
        (< (get newLoc :x) 0) 
        )
    0
    1)
  )

(def RULES_DICT { 
                 :up []
                 :left []
                 :right []
                 :down []
                 :all [bounds]
                 })
