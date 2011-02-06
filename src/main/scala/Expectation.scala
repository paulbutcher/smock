package com.paulbutcher.smock

class Expectation(val name: Symbol) {
  
  def times(n: Int) = {
    invocations.setCount(n)
    this
  }
  def never() = times(0)
  def once() = times(1)
  def twice() = times(2)
  
  def times(range: Range) = this //! TODO

  def atLeast(n: Int) = this //! TODO
  def atLeastOnce() = atLeast(1)
  
  def atMost(n: Int) = this //! TODO
  def atMostOnce() = atMost(1)
  
  def withArgs(args: Any*) = this //! TODO
  
  def returns(value: AnyRef) = {
    invocations.addReturnValue(value)
    this
  }
  
  def throws(exception: Throwable) = this //! TODO
  
  def then() = this
  
  def nextReturnValue = try {
    invocations.next
  } catch {
    case _: NoSuchElementException => throw new ExpectationException("put a sensible message here")
  }
  
  def verify() {
    invocations.verify
  }
  
  private val invocations = new Invocations
}
