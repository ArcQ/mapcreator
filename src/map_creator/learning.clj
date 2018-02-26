(defn learning
  []
  (def testStr "test")
  (def testStr2 (str "test2" testStr))
  ;; (println testStr2)

  (def testMap {:first "charlie" :last "munsch" :middle {:test "viking"}})
  (println (get testMap :first))
  (println (get testMap :none "unicorns?"))
  (println (get-in testMap [:middle :test] "unicorns?"))
  (println (:none testMap "unicorns"))
  (println (:none testMap "unicorns"))
  (println (testMap :last))
  (println (get ["0" {:test 1} "2"] 1) )
  (println (conj ["0" {:test 1} "2"] 1) )
  ;; hash set
  #{"test" 20 :icicle})
