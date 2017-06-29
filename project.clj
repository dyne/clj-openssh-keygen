(defproject clj-openssh-keygen "0.1.0"
  :description "A clojure and java library to genereate OpenSSH compliant keypairs without executing ssh binaries"
  :url "https://dyne.org"

  :dependencies [[org.bouncycastle/bcprov-jdk15on "1.57"]
                 [org.bouncycastle/bcpkix-jdk15on "1.57"]
                 [commons-codec/commons-codec "1.10"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.4.0"]]

  :jvm-opts ["-Djava.security.egd=file:/dev/random" ;use a proper random source
             "-XX:-OmitStackTraceInFastThrow"] ; stacktrace JVM exceptions

  :license {:author "Denis Roio"
            :email "jaromil@dyne.org"
            :year 2017
            :name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :comments "same as Clojure"}

  :deploy-repositories [["releases" {:url :clojars
                                     :creds :gpg}]]

  :java-source-paths ["src/java/"])


