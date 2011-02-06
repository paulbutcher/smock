package com.paulbutcher.smock

abstract class AbstractInvocation {
  
  def hasNext = repeatForever || actual < expected

  def next() = {
    if (!hasNext)
      throw new NoSuchElementException
    actual += 1
    returnValue
  }
  
  def setCount(n: Int) {
    setNotForever
    expected = n
  }
  
  def setNotForever() { repeatForever = false }
  
  def isSatisfied = (repeatForever && actual > 0) || actual == expected
  
  protected def returnValue: AnyRef
    
  protected var repeatForever = true
  protected var expected = 1
  protected var actual = 0
}

class Invocation(protected val returnValue: AnyRef = null) extends AbstractInvocation

class ThrowingInvocation(exception: Throwable) extends AbstractInvocation {
  
  override protected def returnValue: AnyRef = throw exception
}
