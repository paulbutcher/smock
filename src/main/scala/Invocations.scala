package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class Invocations {
  
  def addReturnValue(value: AnyRef) {
    addInvocation
    invocations.last.setReturnValue(value)
  }
  
  def addException(exception: Throwable) {
    addInvocation
    invocations.last.setException(exception)
  }
  
  def addArguments(arguments: Seq[AnyRef]) {
    addInvocation
    invocations.last.setArguments(arguments)
  }
  
  def setCount(n: Int) {
    defaultIfNecessary
    invocations.last.setCount(n)
  }
  
  def handle(arguments: Array[AnyRef]) = {
    defaultIfNecessary
    
    if (current == null || current.exhausted)
      current = iterator.next
    
    current.handle(arguments)
  }
  
  def verify() {
    defaultIfNecessary

    for (invocation <- invocations)
      if (!invocation.isSatisfied)
        throw new ExpectationException("put a sensible message here")
  }
  
  private def addInvocation() {
    if (!invocations.isEmpty)
      invocations.last.setNotForever

    invocations += new Invocation
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
