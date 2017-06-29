(ns clj-openssh-keygen.core-test
  (:require [clojure.test :refer :all]
            [clj-openssh-keygen.core :refer :all]))

(deftest keygen
  (let [kp (generate-key-pair)]
    (write-key-pair kp "testkey")))
