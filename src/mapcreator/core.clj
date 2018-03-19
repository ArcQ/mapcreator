(ns mapcreator.core
  (:require [mapcreator.builder.core :as builder]))

(use 'debugger.core)

(defn system
  []
  {:start (fn
            [& args]
            (run! println (builder/createMap {:x 9 :y 9})))
   :stop (fn stop [])})
