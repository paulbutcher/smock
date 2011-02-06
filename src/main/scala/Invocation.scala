package com.paulbutcher.smock

class Invocation(returnValue: AnyRef = null) {
  
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
    
  private var repeatForever = true
  private var expected = 1
  private var actual = 0
}
