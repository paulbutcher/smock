package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class Invocations {
  
  def +=(returnValue: AnyRef) {
    if (!invocations.isEmpty)
      invocations.last.setNotForever

    invocations += new Invocation(returnValue)
  }
  
  def setCount(n: Int) {
    defaultIfNecessary
    invocations.last.setCount(n)
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

    for (invocation <- invocations)
      if (!invocation.isSatisfied)
        throw new ExpectationException("put a sensible message here")
  }
  
  private def defaultIfNecessary() {
    // Special case - if no invocation has been set, create a default one
    if (invocations.isEmpty)
      invocations += new Invocation
  }
  
  private val invocations = new ListBuffer[Invocation]
  private val iterator = invocations.iterator
  private var current: Invocation = _
}
