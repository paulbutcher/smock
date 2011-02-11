package com.paulbutcher.smock

class Invocation {
  
  def hasReturnValue = returnValue isDefined
  def setReturnValue(v: AnyRef) { returnValue = Some(v) }
  
  def hasException = exception isDefined
  def setException(e: Throwable) { exception = Some(e) }
  
  def hasArguments = expectedArguments isDefined
  def setArguments(a: Seq[AnyRef]) { expectedArguments = Some(a) }
  
  def exhausted = !repeatForever && actual >= expected

  def handle(arguments: Array[AnyRef]) = {
    if (exhausted)
      throw new NoSuchElementException
    actual += 1
    
    if (hasArguments && !(arguments sameElements expectedArguments.get))
      throw new ExpectationException("put a sensible message here")

    if (hasException)
      throw exception.get

    returnValue orNull
  }
  
  def setCount(n: Int) {
    setNotForever
    expected = n
  }
  
  def setNotForever() { repeatForever = false }
  
  def isSatisfied = (repeatForever && actual > 0) || actual == expected
  
  private var returnValue: Option[AnyRef] = None
  private var exception: Option[Throwable] = None
  private var expectedArguments: Option[Seq[AnyRef]] = None
    
  private var repeatForever = true
  private var expected = 1
  private var actual = 0
}
