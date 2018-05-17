(ns mapcreator.builder.shared.utils
  (:require [clojure.walk :refer [postwalk]]))

(defn getLocVal [gameMap loc]
  (-> gameMap
      (get (:y loc))
      (get  (:x loc))))

(defn getBounds [gameMap]
  {:x (count (nth gameMap 0)) :y (count gameMap)})

(defn isWithinBounds [loc gameMap])

(defn sumMap [gameMap]
  (reduce + (flatten gameMap)))

(defn hashFunc [curLoc, gameMap]
  (+
   (* (:y curLoc) (:y (getBounds gameMap)))
   (:x curLoc)))

(defn reverseHashFunc [hashId, gameMap]
  {:x (mod hashId (:y (getBounds gameMap)))
   :y (int (/ hashId (:y (getBounds gameMap))))})

(defn getLocValFromHash [hashId, gameMap]
  ((gameMap (int (/ hashId (:x (getBounds gameMap)))))
   (mod hashId (:x (getBounds gameMap)))))

(defn hashFuncMap [gameMap]
  (let [width (:x (getBounds gameMap))
        height (:y (getBounds gameMap))]
    (map
     (fn [y] (range (* y height) (+ width (* y height))))
     (range height))))

(defn hashFuncMapZeroes [gameMap]
  (map (fn [row]
         (filter
          (fn [hashId] (= 0 (getLocValFromHash hashId gameMap)))
          row))
       (hashFuncMap gameMap)))

(defn removeHashesFromHashMap [])

(defn hashList [gameMap]
  (take
   (* (count gameMap) (count (gameMap 0)))
   (range)))

(defn maxHash [gameMap]
  (- (* (count gameMap) (count (gameMap 0))) 1))

(defn updateElement [gameMap {:keys [x y]} newVal]
  (update-in gameMap [x y]
             (constantly newVal)))
