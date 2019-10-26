(defproject inventory "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Unlicensed"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [proto-repl "0.3.1"]
                 [org.clojure/test.check "0.10.0"]]
  :main ^:skip-aot inventory.core
  :repl-options {:init-ns inventory.core}
  :plugins [[lein-bikeshed "0.5.2"]]
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0"]]}})
