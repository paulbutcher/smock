package com.paulbutcher.smock

import org.scalatest.WordSpec

class MockSpec extends WordSpec {
  
  "A mock" should {
    "Fail if any method is called when no expectations are set" in {
      val t = mock[Turtle]
      intercept[ExpectationException] { t.penDown }
    }
    
    "Succeed if an expected method is called" in {
      val t = mock[Turtle]

      ensure { expectations: Expectations =>
        t.receives('penDown)(expectations)
      } in {
        t.penDown
      }
    }
  }
}
