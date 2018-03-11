(defproject mapcreator "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :plugins []
  :main ^:skip-aot mapcreator.core
  :target-path "target/%s"
  :profiles {
             :uberjar {:aot :all}
             :dev {:main user
                   :source-paths ["dev"]
                   :dependencies [
                                  [org.clojure/tools.namespace "0.2.3"]
                                  [org.clojure/java.classpath "0.2.0"]
                                  ]}
             })
