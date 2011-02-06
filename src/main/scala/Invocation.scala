package com.paulbutcher.smock

class Invocation {
  
  def setReturnValue(v: AnyRef) { returnValue = v }
  
  def setException(e: Throwable) { exception = e }
  
  def setArguments(a: Seq[AnyRef]) { expectedArguments = a }
  
  def exhausted = !repeatForever && actual >= expected

  def handle(arguments: Array[AnyRef]) = {
    if (exhausted)
      throw new NoSuchElementException
    actual += 1
    
    if (expectedArguments != null && !(arguments sameElements expectedArguments))
      throw new ExpectationException("put a sensible message here")

    if (exception != null)
      throw exception
    returnValue
  }
  
  def setCount(n: Int) {
    setNotForever
    expected = n
  }
  
  def setNotForever() { repeatForever = false }
  
  def isSatisfied = (repeatForever && actual > 0) || actual == expected
  
  private var returnValue: AnyRef = _
  private var exception: Throwable = _
  private var expectedArguments: Seq[AnyRef] = _
    
  private var repeatForever = true
  private var expected = 1
  private var actual = 0
}
