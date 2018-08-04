(ns online-code-executor-example.boundary.docker
  (:require [clj-http.client :as http]
            [clojure.data.json :as json]
            [integrant.core :as ig]))

(defrecord Docker [host port])

(defmethod ig/init-key :online-code-executor-example.boundary/docker [_ spec]
  (map->Docker spec))

(defprotocol DockerCodeExecutor
  (find [this lang])
  (execute [this lang source-code]))

(extend-protocol DockerCodeExecutor
  Docker
  (find [this lang]
    (let [result]
      ;; find specific language container
      ))
  (execute [this lang source-code]
    (let [{id :id} (find this lang)]
      (let [result (http/post (str "http://" (:host this) ":" (:port this) "/containers/" id "/exec")
                              {:content-type :json
                               :accept :json
                               :socket-timeout 10000
                               :conn-timeout 10000
                               :body (json/write-str {"AttachStdout" true
                                                      "Tty" true
                                                      "Cmd" ["/usr/local/bin/php" "-r" (str "'" source-code "'")]})})]
        (println "exec result:")
        (println result)
        result))))
