package com.paulbutcher.smock.javaproxy

import java.lang.reflect.{ Method, Proxy, InvocationHandler }

/** A wrapper around java dynamic proxies to make it easy to pose
 *  as an interface.
 * 
 * Taken from scala.tools.reflect.Mock
 */
trait JavaProxy extends (Invoked => AnyRef) {
  javaproxy =>
  
  def interfaces: List[Class[_]]
  def classLoader: ClassLoader
  def apply(invoked: Invoked): AnyRef
  
  def newProxyInstance(handler: InvocationHandler): AnyRef =
    Proxy.newProxyInstance(classLoader, interfaces.toArray, handler)
  def newProxyInstance(): AnyRef =
    newProxyInstance(newInvocationHandler())
  
  def newInvocationHandler() = new InvocationHandler {
    def invoke(proxy: AnyRef, method: Method, args: Array[AnyRef]) =
      javaproxy(Invoked(proxy, method, args))
  }
}

/** The methods in JavaProxy create the actual proxy instance which can be used
 *  in place of the associated interface(s).
 */
object JavaProxy {
  /** The default implementation calls the partial function if defined, and
   *  routes Object methods to the proxy: otherwise it throws an exception.
   */
  def fromInterfaces(clazz: Class[_], clazzes: Class[_]*)(pf: PartialFunction[Invoked, AnyRef]): AnyRef = {
    val ints = clazz :: clazzes.toList
    require(ints forall (_.isInterface), "All class objects must represent interfaces")
    
    val javaproxy = new JavaProxy {
      val interfaces  = ints
      def classLoader = clazz.getClassLoader
      def apply(invoked: Invoked) =
        if (pf.isDefinedAt(invoked)) pf(invoked)
        else if (invoked.isObjectMethod) invoked invokeOn this
        else throw new NoSuchMethodException("" + invoked)
    }
    javaproxy.newProxyInstance()
  }
  /** Tries to implement all the class's interfaces.
   */
  def fromClass(clazz: Class[_])(pf: PartialFunction[Invoked, AnyRef]): AnyRef = allInterfaces(clazz) match {
    case Nil      => error(clazz + " implements no interfaces.")
    case x :: xs  => fromInterfaces(x, xs: _*)(pf)
  }
}
