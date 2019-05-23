(ns mapcreator.api.core
  (:require [compojure.core :as compojure]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :as r]
            [clojure.data.json :as json]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [mapcreator.builder.algorithms.randomfill.add-obstacle :as addObstacle]))

(defn allow-cross-origin [handler]
  (fn [request]
    (let [response (handler request)]
      (-> response
          (r/header "Access-Control-Allow-Origin" "http://localhost:3000")
          (r/header "Access-Control-Max-Age" "86400")
          (r/header "Access-Control-Allow-Methods" "GET, POST, PUT, DELETE")
          (r/header "Access-Control-Allow-Headers" "X-Requested-With")
          (r/header "Access-Control-Allow-Headers" "Auth, Content-Type")
          (r/header "Access-Control-Expose-Headers" "Auth, Content-Type")))))

(compojure/defroutes app
  (compojure/GET "/gamemap/generate" [x y]
    {:body (json/write-str {:gameMap (addObstacle/createInitialMap (Integer/parseInt x) (Integer/parseInt y))})
     :status 200
     :headers {"Content-Type" "text/plain"}}))

(def wrappedApp (-> app
                    (wrap-defaults site-defaults) allow-cross-origin))

(defn startServer []
  (jetty/run-jetty #'wrappedApp {:join? false :port 7000}))
