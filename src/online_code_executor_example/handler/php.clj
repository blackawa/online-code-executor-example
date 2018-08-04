(ns online-code-executor-example.handler.php
  (:require [bidi.bidi :as bidi]
            [integrant.core :as ig]
            [ring.util.anti-forgery :refer [anti-forgery-field]]
            [rum.core :as rum]))

(defn index-view
  ([action]
   (index-view action nil))
  ([action result]
   [:html
    [:head]
    [:body
     [:form {:action action :method "POST"}
      [:span {:dangerouslySetInnerHTML {:__html (anti-forgery-field)}}]
      [:textarea {:name "code" :value "<?php\n// code here"}]
      [:button "run"]]
     [:pre
      result]]]))

(defmethod ig/init-key ::index [_ {:keys [routes]}]
  (fn [req]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (rum/render-html (index-view (bidi/path-for
                                         routes
                                         :online-code-executor-example.handler.php/index)))}))

(defmethod ig/init-key ::create [_ {:keys [routes]}]
  (fn [{:keys [form-params] :as req}]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (rum/render-html
            (index-view
             (bidi/path-for routes
                            :online-code-executor-example.handler.php/index)
             "hoge"))}))
