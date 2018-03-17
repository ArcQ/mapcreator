(ns mapcreator.builder.score)
(load "./utils")
(defn getQuadrants [gameMap]
  (reduce #(assoc %1 %2 1) {} {:ne {:x 1 :y 0}
                               :nw {:x 0 :y 0}
                               :se {:x 1 :y 1}
                               :sw {:x 0 :y 1}}))

(defn getRatio [gameMap]
  (/ (reduce + (flatten gameMap)) (reduce * (vals (getBounds gameMap)))))

(defn passCriteria []
  false)
