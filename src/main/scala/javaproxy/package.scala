package com.paulbutcher.smock

package object javaproxy {
  def allInterfaces(cl: Class[_]): List[Class[_]] =
    if (cl == null) Nil
    else cl.getInterfaces.toList ++ allInterfaces(cl.getSuperclass) distinct
}
