package com.paulbutcher.smock

import java.lang.reflect.{InvocationHandler, Method, Proxy => JavaProxy}

object Proxy {
  def create(clazz: Class[_]) = {
    require(clazz.isInterface, "Only interfaces can be proxied")

    val handler = new InvocationHandler {
      def invoke(proxy: AnyRef, method: Method, args: Array[AnyRef]) = {
        throw new ExpectationException(method.getName + " invoked")
      }
    }
    JavaProxy.newProxyInstance(clazz.getClassLoader, Array(clazz, classOf[Mock]), handler)
  }
}
