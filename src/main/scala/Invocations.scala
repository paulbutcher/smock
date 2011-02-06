package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class Invocations {
  
  def +=(value: AnyRef) {
    if (!values.isEmpty)
      values.last.setNotForever

    values += new Invocation(value)
  }
  
  def setCount(n: Int) {
    defaultIfNecessary
    values.last.setCount(n)
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
  
  def verify() {
    defaultIfNecessary

    for (value <- values)
      if (!value.isSatisfied)
        throw new ExpectationException("put a sensible message here")
  }
  
  private def defaultIfNecessary() {
    // Special case - if no return value has been set, create a default one
    if (values.isEmpty)
      values += new Invocation
  }
  
  private val values = new ListBuffer[Invocation]
  private val iterator = values.iterator
  private var current: Invocation = _
}
