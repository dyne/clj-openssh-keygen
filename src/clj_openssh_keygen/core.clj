;; Copyright (C) 2016-2017 Dyne.org foundation

;; Sourcecode written and maintained by Denis Roio <jaromil@dyne.org>

;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; By using this software in any fashion, you are agreeing to be bound by
;; the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns clj-openssh-keygen.core
  (:refer-clojure :exclude [format])
  (:require [clojure.java.io])
  (:import [org.apache.commons.codec.binary Base64]
           [org.bouncycastle.jce.provider BouncyCastleProvider]
           [java.security Key KeyFactory KeyPair KeyPairGenerator
            MessageDigest PrivateKey PublicKey Security Signature KeyStore]
           [java.security.spec PKCS8EncodedKeySpec X509EncodedKeySpec]
           [java.util Random]
           [java.io InputStream]
           [java.io StringWriter]
           [javax.crypto Cipher]
           [java.nio ByteBuffer]
;           [java.lang String]
           [org.bouncycastle.openssl PEMWriter]
;            [org.bouncycastle.util Strings]
;           [org.bouncycastle.openssl.jcajce JcaPEMWriter]
           [OpenSSHWriter]))

(def default-algorithm "RSA")
; (def default-provider "BC")
(def default-character-encoding "US-ASCII")
(def default-key-size 2048)

(Security/addProvider (new BouncyCastleProvider))

(defn encode-base64 [bindata]
  (Base64/encodeBase64 bindata))

(defn generate-key-pair [& {:keys [key-size algorithm]}]
  (let [key-pair-generator (KeyPairGenerator/getInstance
                            (or algorithm default-algorithm))]
    (.initialize key-pair-generator (or key-size default-key-size))
    (.generateKeyPair key-pair-generator)))

(defn write-key-pair [key-pair path]
  ;; {:pre [(instance? java.security.Keypair key-pair)
  ;;        (string? path)]}
   
  ;; write public key
  (let [pub-writer (new OpenSSHWriter)]
    (with-open [out (clojure.java.io/output-stream (str path ".pub"))]
      (.write out (.getBytes "ssh-rsa "))
      (.write out (encode-base64
                   (.encode pub-writer (.getPublic key-pair))))))

  ;; write private key
  (let [priv-writer (new StringWriter)
        pem-writer (new PEMWriter priv-writer)]
    (.writeObject pem-writer (.getPrivate key-pair))
    (.flush pem-writer)
    (spit path (.toString priv-writer))))
