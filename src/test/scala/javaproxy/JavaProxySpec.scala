package com.paulbutcher.smock.javaproxy

import com.paulbutcher.smock.Turtle

import org.scalatest.WordSpec

class JavaProxySpec extends WordSpec {
  
  "A Java proxy" should {
    "look like an instance of the interface it's proxying" in {
      val p = JavaProxy.fromInterfaces(classOf[Turtle]) {
        case Invoked.NameAndArgs(name, _) => null
      }.asInstanceOf[Turtle]

      assert(p.isInstanceOf[Turtle])
    }
  }
}
