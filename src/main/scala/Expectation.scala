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
  
  def withArgs(arguments: Any*) = {
    invocations.addArguments(arguments.map(_.asInstanceOf[AnyRef]))
    this
  }
  
  def returns(value: Any) = {
    invocations.addReturnValue(value.asInstanceOf[AnyRef])
    this
  }
  
  def throws(exception: Throwable) = {
    invocations.addException(exception)
    this
  }
  
  def then() = {
    invocations.addInvocation
    this
  }
  
  def handle(args: Array[AnyRef]) = decorateException(invocations.handle(args))
  
  def verify() {
    decorateException(invocations.verify)
  }
  
  private def decorateException[T](f: => T) = try {
    f
  } catch {
    case e: Exception => throw new ExpectationException("Expected "+ name.name +" "+ e.getMessage)
  }
  
  private val invocations = new Invocations
}
