(ns mapcreator.builder.score)
(load "./utils")

(defn getSubQuadrant [quadDict gameMap]
  (-> (getBounds gameMap)
      (#(assoc % :xBound (/ (:x %) 2) :yBound (/ (:y %) 2)))
      (#(as->
         (partition (:yBound %) gameMap) v
         (nth v (:yQuad quadDict))
         (map (fn [row] (-> (partition (:xBound %) row)
                            (nth (:xQuad quadDict))))
              v)))))

(defn getQuadrants [gameMap]
  (reduce #(assoc %1 (%2 0) (getSubQuadrant (%2 1) gameMap)) {} {:ne {:xQuad 1 :yQuad 0}
                                                                 :nw {:xQuad 0 :yQuad 0}
                                                                 :se {:xQuad 1 :yQuad 1}
                                                                 :sw {:xQuad 0 :yQuad 1}}))

(defn getRatio [gameMap]
  (/ (sumMap gameMap) (reduce * (vals (getBounds gameMap)))))

(defn noEmptyQuadrants [gameMap]
  (->> (vals (getQuadrants gameMap))
       (map #(sumMap %))
       (reduce #(and %1 (>= %2 3)) true)))

(defn passCriteria [{:keys [minRatio, minPerQuadrant]}]
  false)
