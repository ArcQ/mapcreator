(ns user
  (:require 
    [clojure.tools.namespace.repl :refer (refresh refresh-all)]
    [map-creator.core :as system]))

;; http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded

(def system nil)

(defn init
  "Constructs the current development system."
  []
  (alter-var-root #'system
                  (constantly (system/system))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'system (get system :start)))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'system
                  (fn [s] (when s ((get system :start) s)))))

(defn go
  "Initializes the current development system and starts it running."
  []
  (init)
  (start))


(defn -main
  [& args]
  (go))
