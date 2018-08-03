(defproject online-code-executor-example "1.0.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 ;; component lifecycle management
                 [aero "1.1.3"]
                 [integrant "0.6.3"]
                 ;; AP server
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [metosin/muuntaja "0.5.0"]
                 ;; view
                 [rum "0.11.2"]
                 ;; others
                 [bidi "2.1.3"]
                 [bouncer "1.0.1"]
                 [buddy/buddy-sign "2.2.0"]
                 [com.taoensso/timbre "4.10.0"]]
  :main ^:skip-aot online-code-executor-example.app
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[integrant/repl "0.3.1"]
                                  [eftest "0.5.2"]
                                  [ring/ring-mock "0.3.2"]
                                  [com.gearswithingears/shrubbery "0.4.1"]]
                   :source-paths ["dev/src" "src"]
                   :repl-options {:init-ns user}}})
