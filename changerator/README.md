# changerator

Calculates the number of coins needed to make change for the given dollar amount.
Currently, the coins accepted are standard USD coins. The program could be altered
to accept other currencies. For testing purposes, you can change coin definitions
in src/changerator/coin.clj.

## Installation

Clone from https://bitbucket.org/nicholasrussell/changerator

## Usage

```
$ lein run [args]
```

or

```
$ lein uberjar

$ java -jar target/uberjar/changerator-0.1.0-standalone.jar [args]
```

I've also checked in a built jar in case you don't want to install Leiningen to run
```
$ java -jar dist/changerator-0.1.0-standalone.jar [args]
```

## Options

The program accepts one argument: the dollar amount for which to make change.
The dollar amount must be specified as a positive number (<= Integer MAX_VALUE) with up to two decimal places (<= .99).
It will accept dollar amounts as either a number, or with prefixed USD signage
(e.g. 1.91 or $1.91, but careful on the command line. You may need to escape the dollar sign \$).
Other currency symbols, such as € or £, will be treated as not a number, since
this program only handles USD coinage.
NOTE: You can create your own fake coins, so this isn't entirely valid, but it's nice to assume.

## Testing

```
$ lein test
```
