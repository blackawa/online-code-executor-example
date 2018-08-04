(ns online-code-executor-example.boundary.docker
  (:require [integrant.core :as ig]))

(defrecord Docker [spec])

(defmethod ig/init-key :online-code-executor-example.boundary/docker [_ spec]
  (->Docker spec))

(defprotocol DockerCodeExecutor
  (find [this lang])
  (run [this lang source-code]))

(extend-protocol DockerCodeExecutor
  Docker
  (find [this lang] {:container "hoge"})
  (run [this lang source-code] {:status 0 :stdout "outoutout" :stderr "errerrerr"}))
