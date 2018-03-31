(ns mapcreator.builder.shared.utils)

(defn getLocVal [gameMap loc]
  (-> gameMap
      (get (:y loc))
      (get  (:x loc))))

(defn getBounds [gameMap]
  {:x (count (gameMap 0)) :y (count gameMap)})

(defn isWithinBounds [loc gameMap])

(defn sumMap [gameMap]
  (reduce + (flatten gameMap)))

(defn hashFunc [curLoc, gameMap]
  (+
   (* (:y curLoc) (:y (getBounds gameMap)))
   (:x curLoc)))

(defn hashList [gameMap]
  (take
   (* (count gameMap) (count (gameMap 0)))
   (range)))

(defn maxHash [gameMap]
  (- (* (count gameMap) (count (gameMap 0))) 1))

(defn reverseHashFunc [hashId, gameMap]
  {:x (mod hashId (:y (getBounds gameMap)))
   :y (int (/ hashId (:y (getBounds gameMap))))})

(defn getLocValFromHash [hashId, gameMap]
  ((gameMap (int (/ hashId (:x (getBounds gameMap)))))
   (mod hashId (:x (getBounds gameMap)))))
