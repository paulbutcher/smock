package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class Expectations {
  val expectations = new ListBuffer[Expectation]
  
  def add(expectation: Expectation) {
    expectations += expectation
  }
  
  def handle(name: Symbol, args: Array[AnyRef]) = {
    if (!expectations.exists(_.name == name))
      throw new ExpectationException(name +" not expected")
    null
  }
}
