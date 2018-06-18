(ns mapcreator.builder.core
  (:require [mapcreator.builder.algorithms.randomfill.controller :as randomfill]
            [mapcreator.builder.algorithms.naive.controller :as naive]
            [mapcreator.builder.algorithms.randomfill.a_star :as a_star]
            [mapcreator.builder.shared.utils :as utils]))

(defn blankGameMap
  [{:keys [x y] :as dimensions}]
  (def vectorOf #(vec (replicate %1 %2)))
  (vectorOf y (vectorOf x 0)))

(defn createMap
  [{:keys [x y] :as dimensions}]
  (let [initialGameMap (blankGameMap dimensions)
        ;; start {:x  0 :y 0}
        ;; end {:x (dec (:x dimensions)) :y (dec (:y dimensions))}]
        start {:x  2 :y 0}
        end {:x (dec 6) :y (dec (:y dimensions))}]
    (randomfill/createPath
     start
     end
     initialGameMap)))
