package com.paulbutcher.smock

import org.scalatest.WordSpec

class MockTest extends WordSpec with MockFactory {
  
  "A mock" should {
    
    "succeed if no expectations are setup and no methods are called" in {
      val t = mock[Turtle]
    }
    
    "fail if an unexpected method is called" in {
      val t = mock[Turtle]
      intercept[ExpectationException] { t.penDown }
    }
    
    "succeed if an expected method is called" in {
      val t = mock[Turtle]
      t.expects('penDown)
      t.penDown
    }
    
    "return a value from a mock" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((10.0, 1.2))
      expect((10.0, 1.2)) { t.getPosition }
    }
    
    "return a sequence of values from a mock" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((1.0, 2.0)).then.
        returns((3.0, 4.0)).then.
        returns((5.0, 6.0))
      
      expect((1.0, 2.0)) { t.getPosition }
      expect((3.0, 4.0)) { t.getPosition }
      expect((5.0, 6.0)) { t.getPosition }
    }
  }
}
