(ns online-code-executor-example.util.response)

(defn ok [body]
  {:status 200 :body body})

(defn created
  ([body] (created body nil))
  ([body location] {:status 201 :body body :location location}))

(defn updated [body]
  {:status 204 :body body})

(defn destroyed [body]
  {:status 200 :body body})

(defn bad-request
  ([body] {:status 400 :body body}))

(defn not-found [body]
  {:status 404 :body body})
