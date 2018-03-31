(def DIRECTIONS [
                 { :name "up" :chance 1 :move #(update % :y dec)}
                 { :name "left" :chance 1 :move #(update % :x dec)}
                 { :name "right" :chance 1 :move #(update % :x inc)}
                 { :name "down" :chance 1 :move #(update % :y inc)}
                 ])
