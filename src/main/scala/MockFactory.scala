package com.paulbutcher.smock

import org.scalatest.{BeforeAndAfterEach, Suite}

trait MockFactory extends BeforeAndAfterEach { this: Suite =>
  
  override def beforeEach() {
    expectations = new Expectations
  }
  
  override def afterEach() {
    if (autoVerify) {
      //! TODO - verify expectations
    }
  }
  
  protected trait Mock {
    def expects(name: Symbol): Expectation
  }
  
  protected def mock[T: ClassManifest] = {
    Proxy.create(classManifest[T].erasure, classOf[Mock]) { (name: Symbol, args: Array[AnyRef]) =>
      name match {
        case 'expects => createExpectation(args(0).asInstanceOf[Symbol])
        case _ => expectations.handle(name, args)
      }
    }.asInstanceOf[T with Mock]
  }
  
  protected def disableAutoVerify() {
    autoVerify = false
  }
  
  protected def verifyExpectations() {
    expectations.verify
  }

  private var expectations: Expectations = _
  
  private def createExpectation(name: Symbol) = {
    val expectation = new Expectation(name)
    expectations.add(expectation)
    expectation
  }
  
  private var autoVerify = true
}
