(ns online-code-executor-example.handler.php
  (:require [bidi.bidi :as bidi]
            [integrant.core :as ig]
            [rum.core :as rum]))

(defn index-view [action]
  [:html
   [:head]
   [:body
    [:form {:action action :method "POST"}
     [:textarea {:name "code"}]
     [:button "run"]]]])

(defmethod ig/init-key ::index [_ {:keys [routes]}]
  (fn [req] {:status 200
             :headers {"Content-Type" "text/html"}
             :body (rum/render-html (index-view (bidi/path-for
                                                 routes
                                                 :online-code-executor-example.handler.php/index)))}))

(defmethod ig/init-key ::create [_ {:keys [routes]}]
  (fn [{:keys [form-params] :as req}]
    {:status 303
     :headers
     {"Location"
      (bidi/path-for
       routes
       :online-code-executor-example.handler.php/index)}}))
