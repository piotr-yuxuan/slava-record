(defproject com.github.piotr-yuxuan/slava-record (-> "./resources/slava-record.version" slurp .trim)
  :description "FIXME cljdoc"
  :github/private? false
  :url "https://github.com/piotr-yuxuan/slava-record"
  :license {:name "European Union Public License 1.2 or later"
            :url "https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12"
            :distribution :repo}
  :scm {:name "git"
        :url "https://github.com/piotr-yuxuan/slava-record"}
  :pom-addition [:developers [:developer
                              [:name "胡雨軒 Петр"]
                              [:url "https://github.com/piotr-yuxuan"]]]
  :dependencies [[potemkin/potemkin "0.4.7"]]
  :global-vars {*warn-on-reflection* true}
  :aot :all
  :profiles {:dev {:jvm-opts ["-Dclojure.compiler.disable-locals-clearing=true"]}
             :test {:dependencies [[com.bakdata.fluent-kafka-streams-tests/schema-registry-mock "2.13.1"]
                                   [org.apache.kafka/kafka-clients "7.6.1-ce"]
                                   [org.apache.kafka/kafka-streams-test-utils "7.6.1-ce"]]}
             :jar {:jvm-opts ["-Dclojure.compiler.disable-locals-clearing=false"
                              "-Dclojure.compiler.direct-linking=true"]}
             :provided {:dependencies [[org.apache.avro/avro "1.11.3"]
                                       [org.clojure/clojure "1.12.0-alpha11"]]}
             :kaocha [:test {:dependencies [[lambdaisland/kaocha "1.88.1376"]]}]}
  :repositories [["confluent" {:url "https://packages.confluent.io/maven/"}]]
  :deploy-repositories [["clojars" {:sign-releases false
                                    :url "https://clojars.org/repo"
                                    :username :env/CLOJARS_USERNAME
                                    :password :env/CLOJARS_TOKEN}]])
