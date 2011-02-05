package com.paulbutcher

package object smock {

  def mock[T: ClassManifest]: T with Mock = Proxy.create(classManifest[T].erasure).asInstanceOf[T with Mock]
  
  def ensure(setup: (Expectations) => Unit): Expectations = {
    val expectations = new Expectations
    setup(expectations)
    expectations
  }
}
