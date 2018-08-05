(ns mapcreator.core
  (:require [mapcreator.builder.core :as builder])
  (:require [mapcreator.api.core :as api]))

(use 'debugger.core)

(defn system
  []
  {:start (fn [& args] (api/startServer))
   :stop (fn stop [])})

(defn createMap []
  (run! println (builder/createMap {:x 9 :y 9})))
