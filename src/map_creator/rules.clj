(defn bounds [{:keys [direction gameMap]}]
  10
  )

(def RULES_DICT { 
                 :up [bounds]
                 :left [bounds]
                 :right [bounds]
                 :down [bounds]
                 :all [bounds]
                 })
