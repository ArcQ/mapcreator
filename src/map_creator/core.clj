(ns map-creator.core
  (:gen-class)
  (:require [ map-creator.builder.core :as builder :refer :all])
  )

(use 'debugger.core)

(defn system
  []
  {:start (fn
            [& args]
            (def initialGameMap (builder/blankGameMap {:x 9 :y 9}))
            (run! println (builder/createPath {:x 8 :y 8} initialGameMap)))
   :stop (fn stop [] )}
  )
