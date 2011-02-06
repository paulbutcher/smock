package com.paulbutcher.smock

import org.scalatest.Suite

trait MockFactory { this: Suite =>
  
  protected trait Mock {
    def expects(name: Symbol): Expectation
  }
  
  protected def mock[T: ClassManifest] = {
    Proxy.create(classManifest[T].erasure, classOf[Mock]) { (name: Symbol, args: Array[AnyRef]) =>
      name match {
        case 'expects => createExpectation(Symbol(args(0).asInstanceOf[String]))
        case _ => throw new ExpectationException(name +" not expected")
      }
    }.asInstanceOf[T with Mock]
  }

  private val expectations = new Expectations
  
  private def createExpectation(name: Symbol) = {
    val expectation = new Expectation(name)
    expectations.add(expectation)
    expectation
  }
}
