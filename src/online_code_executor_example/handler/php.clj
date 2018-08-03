(ns online-code-executor-example.handler.php
  (:require [bidi.bidi :as bidi]
            [integrant.core :as ig]))

(defmethod ig/init-key ::index [_ deps]
  (fn [req] {:status 200
             :headers {"Content-Type" "text/html"}
             :body "<!doctype html><html><h1>Hello world</h1></html>"}))

(defmethod ig/init-key ::create [_ {:keys [routes]}]
  (fn [req] {:status 303
             :headers
             {"Location"
              (bidi/path-for
               routes
               :online-code-executor.handler.php/index)}}))
