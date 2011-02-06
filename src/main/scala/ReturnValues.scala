package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class ReturnValue(value: AnyRef = null) {
  
  def hasNext = repeatForever || actual < expected

  def next() = {
    if (!hasNext)
      throw new NoSuchElementException
    actual += 1
    value
  }
  
  def count = expected
  def count_=(n: Int) {
    repeatForever = false
    expected = n
  }
  
  def forever = repeatForever
  def forever_=(b: Boolean) { repeatForever = b }
    
  private var repeatForever = true
  private var expected = 1
  private var actual = 0
}

class ReturnValues {
  
  def +=(value: AnyRef) {
    if (!values.isEmpty)
      values.last.forever = false

    values += new ReturnValue(value)
  }
  
  def count_=(n: Int) {
    defaultIfNecessary
    values.last.count = n
  }
  
  def next() = {
    defaultIfNecessary
    
    if (current != null && current.hasNext) {
      current.next
    } else {
      current = iterator.next
      current.next
    }
  }
  
  private def defaultIfNecessary() {
    // Special case - if no return value has been set, create a default one
    if (values.isEmpty)
      values += new ReturnValue
  }
  
  private val values = new ListBuffer[ReturnValue]
  private val iterator = values.iterator
  private var current: ReturnValue = _
}
