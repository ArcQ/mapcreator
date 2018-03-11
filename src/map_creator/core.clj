(ns map-creator.core
  (:gen-class)
  )

(load "./builder/main")
(use 'debugger.core)

(defn system
  []
  {:start (fn
            [& args]
            (def initialGameMap (blankGameMap {:x 9 :y 9}))
            (run! println (createPath {:x 8 :y 8} initialGameMap)))
   :stop (fn stop [] )}
  )
