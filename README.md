# clj-OpenSSH-keygen

<a href="https://www.dyne.org"><img
	src="https://secrets.dyne.org/static/img/swbydyne.png"
		alt="software by Dyne.org"
			title="software by Dyne.org" class="pull-right"></a>

[![Clojars Project](https://clojars.org/org.clojars.dyne/clj-openssh-keygen/latest-version.svg)](https://clojars.org/org.clojars.dyne/clj-openssh-keygen)

A platform-independent Clojure library to genereate OpenSSH compliant
key-pairs without executing any ssh binary on the system.

## Usage

To generate a keypair `testkey.pub` and `testkey` in the current
execution directory:

```clj
  (let [kp (generate-key-pair)]
    (write-key-pair kp "testkey")))
```

## License

Copyright (C) 2016-2017 Dyne.org foundation

Sourcecode written and maintained by Denis Roio <jaromil@dyne.org>

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
