(ns mapcreator.builder.ruleDict)

(load "./directions")

(defn getLocVal [gameMap loc]
  (-> gameMap
      (get (:y loc))
      (get  (:x loc))))

(defn getSuroundings [gameMap curLoc]
  (reduce
   #(assoc %1
           (keyword (:name %2))
           (-> curLoc
               ((:move %2))
               ((partial getLocVal gameMap))))
   {} DIRECTIONS))

(defn bounds [{:keys [direction gameMap curLoc]}]
  (def gameMapSize {:x (count (get gameMap 0)) :y (count gameMap)})
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
  (if (= (get-in gameMap [(:y nextLoc) (:x nextLoc)]) 1)
    0
    1))

(defn noTouchingPaths [{:keys [direction gameMap curLoc]}]
  (def nextLoc ((:move direction) curLoc))
  (if (= (get-in gameMap [(:y nextLoc) (:x nextLoc)]) 1)
    0
    1))

(def RULE_DICT {:up []
                :left []
                :right []
                :down []
                :all [bounds noBack]})
