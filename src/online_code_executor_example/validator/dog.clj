(ns online-code-executor-example.validator.dog
  (:require [bouncer.core :as b]
            [bouncer.validators :as v]))

(defn validate-create [dog]
  (b/validate dog
              :breed [v/required v/string]
              :name [v/required v/string]))

(defn validate-update [dog]
  (b/validate dog
              :id [v/required v/integer]
              :breed [v/required v/string]
              :name [v/required v/string]))
