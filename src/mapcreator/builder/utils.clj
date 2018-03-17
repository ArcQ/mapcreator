(defn getLocVal [gameMap loc]
  (-> gameMap
      (get (:y loc))
      (get  (:x loc))))

(defn getBounds [gameMap]
  {:x (count (gameMap 0)) :y (count gameMap)})
