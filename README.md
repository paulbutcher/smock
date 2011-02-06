# Smock

A (very) early cut of a native Scala mocking framework. The interface is heavily influenced by Ruby's [Mocha](http://mocha.rubyforge.org/).

There's a lot missing and a lot that doesn't work, but the basics are here.

## Building

Build with [sbt](http://code.google.com/p/simple-build-tool/):

    sbt update
    sbt test

## Examples

See `src/test/scala/example` for a reworked version of the example from Martin Fowler's [Mocks Aren't Stubs](http://martinfowler.com/articles/mocksArentStubs.html).

## Design Principles

Existing Java mocking libraries work with Scala but:

* Unsurprisingly, they don't handle things that Java doesn't support, such as curried functions, higher-order functions, etc.
* For my sins, I find myself developing on Android, which none of the Java mocking libraries support (because the Dalvik VM doesn't support runtime code generation).

Smock's inner workings were inspired by `scala.tools.reflect.Mock` written by Paul Philips in the Scala compiler. In particular, there's no dependency on [cglib](http://cglib.sourceforge.net/), meaning that it runs just fine on Android.

## To do

* Documentation
* Integration with testing frameworks other than ScalaTest
* Meaningful error messages
* Argument matchers
* Specification of call ordering
* Android
