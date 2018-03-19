(ns mapcreator.builder.core
  (:require [mapcreator.builder.algorithms.naive.controller :as naive]))

(defn blankGameMap
  [{:keys [x y] :as dimensions}]
  (def vectorOf #(vec (replicate %1 %2)))
  (vectorOf y (vectorOf x 0)))

(defn createMap
  [{:keys [x y] :as dimensions}]
  (def initialGameMap (blankGameMap dimensions))
  (naive/createPath
   {:x (dec (:x dimensions)) :y (dec (:y dimensions))}
   ;; (zipmap (keys dimensions) (map inc (vals dimensions)))
   initialGameMap))
