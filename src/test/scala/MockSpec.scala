package com.paulbutcher.smock

import org.scalatest.WordSpec

class MockSpec extends WordSpec with MockFactory {
  
  "A mock" should {
    
    "Succeed if an expected method is called" in {
      
      val t = mock[Turtle]
      t.expects('penDown).once
      t.penDown
    }
  }
}
