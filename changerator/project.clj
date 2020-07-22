(defproject changerator "0.1.0"
  :description "Calculates number of coins needed to make change for a given amount"
  :url "https://bitbucket.org/nicholasrussell/changerator"
  :license {}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot changerator.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
