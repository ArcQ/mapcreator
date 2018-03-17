(ns mapcreator.builder.ruleDict)

(load "./directions")
(load "./utils")

(defn getSurroundings [gameMap curLoc]
  (reduce
   #(assoc %1
           (keyword (:name %2))
           (-> curLoc
               ((:move %2))
               ((partial getLocVal gameMap))))
   {} DIRECTIONS))

(defn bounds [{:keys [direction gameMap curLoc]}]
  (def gameMapSize (getBounds gameMap))
  (def nextLoc ((:move direction) curLoc))
  (if (or
       (> (:x nextLoc) (- (:x gameMapSize) 1))
       (> (:y nextLoc) (- (:y gameMapSize) 1))
       (< (:x nextLoc) 0)
       (< (:x nextLoc) 0))
    0
    1))

(defn noBack [{:keys [direction gameMap curLoc]}]
  (def nextLoc ((:move direction) curLoc))
  (if (> (get-in gameMap [(:y nextLoc) (:x nextLoc)]) 0)
    0
    1))

(defn noTouchingPaths [{:keys [direction gameMap curLoc]}]
  (def nextLoc ((:move direction) curLoc))
  (if (> (reduce + (remove nil? (vals (getSurroundings gameMap nextLoc)))) 1)
    0
    1))

(def RULE_DICT {:up []
                :left []
                :right []
                :down []
                :all [bounds noBack noTouchingPaths]})
