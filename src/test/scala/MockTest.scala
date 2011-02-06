package com.paulbutcher.smock

import org.scalatest.WordSpec

class MockTest extends WordSpec with MockFactory {
  
  "A mock" should {
    
    "Succeed if no expectations are setup and no methods are called" in {
      val t = mock[Turtle]
    }
    
    "Fail if an unexpected method is called" in {
      val t = mock[Turtle]
      intercept[ExpectationException] { t.penDown }
    }
    
    "Succeed if an expected method is called" in {
      val t = mock[Turtle]
      t.expects('penDown)
      t.penDown
    }
  }
}
