package com.paulbutcher.smock

class Expectation(mock: Mock, name: Symbol) {
  
  def times(n: Int) = this //! TODO
  def times(range: Range) = this //! TODO
  def once() = times(1)
  def twice() = times(2)
  
  def atLeast(n: Int) = this //! TODO
  def atLeastOnce() = atLeast(1)
  
  def atMost(n: Int) = this //! TODO
  def atMostOnce() = atMost(1)
  
  def withArgs(args: Any*) = this //! TODO
  
  def returns(value: Any) = this //! TODO
  
  def throws(exception: Throwable) = this //! TODO
  
  def then() = this
}
