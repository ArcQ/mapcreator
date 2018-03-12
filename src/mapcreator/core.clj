(ns mapcreator.core
  (:require [mapcreator.builder.core :as builder]))

(use 'debugger.core)

(defn system
  []
  {:start (fn
            [& args]
            (def initialGameMap (builder/blankGameMap {:x 9 :y 9}))
            (run! println (builder/createPath {:x 8 :y 8} initialGameMap)))
   :stop (fn stop [])})
