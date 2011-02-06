package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class Expectations {
  val expectations = new ListBuffer[Expectation]
  
  def add(expectation: Expectation) {
    expectations += expectation
  }
  
  def handle(name: Symbol, args: Array[AnyRef]) = {
    expectations.find(_.name == name) match {
      case Some(expectation) => expectation.handle(args)
      case None => throw new ExpectationException(name +" not expected")
    }
  }
  
  def verify() {
    expectations.foreach(_.verify)
  }
}
