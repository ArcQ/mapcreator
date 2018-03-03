(ns map-creator.core
  (:gen-class))

(load "./rules")

(def DIRECTIONS [
                 {
                  :name "up" :chance 1.1 :move #(update % :y dec)}
                 {
                  :name "left" :chance 1 :move #(update % :x dec)}
                 {
                  :name "right" :chance 1 :move #(update % :x inc)}
                 {
                  :name "down" :chance 1 :move #(update % :y inc)}
                 ])

(defn applyRules [direction] 
  direction)

(defn blankGameMap
  [{:keys [x y] :as dimensions}]
  (def vectorOf #(vec (replicate %1 %2)))
  (vectorOf y (vectorOf x 0)))

(defn modifyChance
  [direction gameMap curLoc]
  ;; (def rulesModifier (map #(% direction gameMap) 
  ;;                         (get RULES_DICT (keyword (get direction :name)))))
  (def rulesModifier (->> (select-keys RULES_DICT [(keyword (get direction :name)) :all])
                          (vals)
                          (flatten)
                          (into [])
                          (reduce #(* %1 (%2 {:direction direction :gameMap gameMap :curLoc curLoc})) 1)))
  (def modifier (* (rand-int 100) rulesModifier))

  (applyRules [direction])
  (update-in direction [:chance] #(* modifier %)))

(defn getDirection [gameMap curLoc]
  (def dirDict 
    (into [] (map #(modifyChance % gameMap curLoc) DIRECTIONS)))
  (apply max-key :chance dirDict))

(defn move
  [{:keys [x, y] :as curLoc} gameMap]
  (def direction (getDirection gameMap curLoc))
  ((get direction :move) curLoc)
  )

(defn createPath
  [{:keys [x, y] :as initialLoc}, initialGameMap]
  (loop [iteration 0 curLoc initialLoc curGameMap initialGameMap]
    (def newGameMap (update-in curGameMap [(get curLoc :y)] #(assoc % (get curLoc :x) 1)))
    (def newLoc (move curLoc newGameMap))
    (if (or (> iteration 100) (< (get newLoc :y) 0) (< (get newLoc :x) 0))
      curGameMap
      (recur (inc iteration) newLoc newGameMap)))
  )

(defn -main
  [& args]
  (def initialGameMap (blankGameMap {:x 10 :y 10}))
  (run! println (createPath {:x 9 :y 9} initialGameMap)))
