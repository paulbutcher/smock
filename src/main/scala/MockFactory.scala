package com.paulbutcher.smock

import org.scalatest.Suite

trait MockFactory { this: Suite =>
  
  trait Mock {
    def expects(name: Symbol): Expectation
  }
  
  def mock[T: ClassManifest] = {
    Proxy.create(classManifest[T].erasure, classOf[Mock]) { (name: Symbol, args: Array[AnyRef]) =>
      name match {
        case 'expects => new Expectation(name)
        case _ => throw new ExpectationException(name +" not expected")
      }
    }.asInstanceOf[T with Mock]
  }
}
