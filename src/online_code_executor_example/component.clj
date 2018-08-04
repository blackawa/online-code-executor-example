(ns online-code-executor-example.component
  (:require [bidi.ring :refer [make-handler]]
            [integrant.core :as ig]
            [muuntaja.middleware :refer [wrap-format]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [taoensso.timbre :as timbre]))

(defmethod ig/init-key ::http [_ {:keys [handler] :as opts}]
  (jetty/run-jetty handler (-> opts (dissoc :handler) (assoc :join? false))))
(defmethod ig/halt-key! ::http [_ server]
  (.stop server))

(defmethod ig/init-key ::endpoint [_ {:keys [handlers routes middleware]}]
  (-> routes
      (make-handler #(handlers %))
      middleware))

(defmethod ig/init-key ::routes [_ routes]
  routes)

(defprotocol Logger
  (log [this level message data]))
(defrecord TimbreLogger [config]
  Logger
  (log [this level message data]
    (timbre/log* config level message data)))
(defmethod ig/init-key ::logger [_ options]
  (map->TimbreLogger
   {:config
    (assoc options :appenders {:println (timbre/println-appender)})}))

(defn wrap-log [handler logger]
  (fn [req]
    (log logger :debug ::receive-request req)
    (try
      (handler req)
      (catch Exception e
        (log logger :fatal ::unexpected-exception {:exception e})
        (throw e)))))

(defmethod ig/init-key ::middleware [_ {:keys [logger dev?]}]
  (fn [handler] (-> handler
                    (wrap-defaults site-defaults)
                    wrap-format
                    (wrap-log logger))))
