package com.paulbutcher.smock

import java.lang.reflect.{InvocationHandler, Method, Proxy => JavaProxy}

object Proxy {
  
  def interfacesFor(clazz: Class[_]): Array[Class[_]] = 
    if (clazz.isInterface)
      Array(clazz)
    else 
      clazz.getInterfaces

  def create(classes: Class[_]*)(f: (Symbol, Array[AnyRef]) => AnyRef) = {
    
    val interfaces = for (clazz <- classes; interface <- interfacesFor(clazz)) yield interface

    val classLoader = classes(0).getClassLoader

    val handler = new InvocationHandler {
      def invoke(proxy: AnyRef, method: Method, args: Array[AnyRef]) =
        f(Symbol(method.getName), args)
    }

    JavaProxy.newProxyInstance(classLoader, interfaces.distinct.toArray, handler)
  }
}
