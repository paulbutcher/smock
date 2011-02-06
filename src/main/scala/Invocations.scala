package com.paulbutcher.smock

import scala.collection.mutable.ListBuffer

class Invocations {
  
  def addReturnValue(value: AnyRef) {
    if (invocations.last.hasReturnValue)
      addInvocation
    invocations.last.setReturnValue(value)
  }
  
  def addException(exception: Throwable) {
    if (invocations.last.hasException)
      addInvocation
    invocations.last.setException(exception)
  }
  
  def addArguments(arguments: Seq[AnyRef]) {
    if (invocations.last.hasArguments)
      addInvocation
    invocations.last.setArguments(arguments)
  }
  
  def setCount(n: Int) {
    invocations.last.setCount(n)
  }
  
  def handle(arguments: Array[AnyRef]) = {
    if (current == null || current.exhausted)
      current = iterator.next
    
    current.handle(arguments)
  }
  
  def verify() {
    for (invocation <- invocations)
      if (!invocation.isSatisfied)
        throw new ExpectationException("put a sensible message here")
  }
  
  private def addInvocation() {
    if (!invocations.isEmpty)
      invocations.last.setNotForever

    invocations += new Invocation
  }
  
  private val invocations = ListBuffer(new Invocation)
  private val iterator = invocations.iterator
  private var current: Invocation = _
}
