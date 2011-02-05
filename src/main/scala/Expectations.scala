package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class Expectations {
  val expectations = new ListBuffer[Expectation]
  
  def add(expectation: Expectation) {
    expectations += expectation
  }
  
  def in(test: => Unit) {
    test
    // TODO - verify expectations
  }
}
