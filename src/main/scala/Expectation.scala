package com.paulbutcher.smock

class Expectation(val name: Symbol) {
  
  def times(n: Int) = this //! TODO
  def times(range: Range) = this //! TODO
  def never() = times(0)
  def once() = times(1)
  def twice() = times(2)
  
  def atLeast(n: Int) = this //! TODO
  def atLeastOnce() = atLeast(1)
  
  def atMost(n: Int) = this //! TODO
  def atMostOnce() = atMost(1)
  
  def withArgs(args: Any*) = this //! TODO
  
  def returns(value: AnyRef) = {
    returnValues += value
    this
  }
  
  def throws(exception: Throwable) = this //! TODO
  
  def then() = this
  
  def nextReturnValue = returnValues.next
  
  private val returnValues = new ReturnValues
}
