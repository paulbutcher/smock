package com.paulbutcher.smock

import org.scalatest.WordSpec

class MockTest extends WordSpec with MockFactory {

  disableAutoVerify
  
  "A mock" should {
    
    "succeed if no expectations are setup and no methods are called" in {
      verifyExpectations
    }
    
    "fail if an unexpected method is called" in {
      val t = mock[Turtle]
      intercept[ExpectationException] { t.penDown }
    }
    
    "succeed if an expected method is called" in {
      val t = mock[Turtle]
      t.expects('penDown)
      t.penDown
      verifyExpectations
    }
    
    "verify that a method is called the right number of times" in {
      val t = mock[Turtle]
      t.expects('penDown).times(3)
      t.penDown
      t.penDown
      t.penDown
      intercept[ExpectationException] { t.penDown }
    }
    
    "verify that a method should never be called" in {
      val t = mock[Turtle]
      t.expects('penDown).never
      intercept[ExpectationException] { t.penDown }
    }
    
    "return a value from a mock" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((10.0, 1.2))
      expect((10.0, 1.2)) { t.getPosition }
    }
    
    "return the same value repeatedly" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((10.0, 1.2))
      expect((10.0, 1.2)) { t.getPosition }
      expect((10.0, 1.2)) { t.getPosition }
      expect((10.0, 1.2)) { t.getPosition }
    }
    
    "return the value once" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((10.0, 1.2)).once
      expect((10.0, 1.2)) { t.getPosition }
      intercept[ExpectationException] { t.getPosition }
    }
    
    "return the value twice" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((10.0, 1.2)).twice
      expect((10.0, 1.2)) { t.getPosition }
      expect((10.0, 1.2)) { t.getPosition }
      intercept[ExpectationException] { t.getPosition }
    }
    
    "return the value an arbitrary number of times" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((10.0, 1.2)).times(4)
      expect((10.0, 1.2)) { t.getPosition }
      expect((10.0, 1.2)) { t.getPosition }
      expect((10.0, 1.2)) { t.getPosition }
      expect((10.0, 1.2)) { t.getPosition }
      intercept[ExpectationException] { t.getPosition }
    }
    
    "return a sequence of values" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((1.0, 2.0)).then.
        returns((3.0, 4.0)).then.
        returns((5.0, 6.0))
      
      expect((1.0, 2.0)) { t.getPosition }
      expect((3.0, 4.0)) { t.getPosition }
      expect((5.0, 6.0)) { t.getPosition }

      info("The last value should repeat indefinitely")
      expect((5.0, 6.0)) { t.getPosition }
      expect((5.0, 6.0)) { t.getPosition }
    }
    
    "return a sequence of values with counts" in {
      val t = mock[Turtle]
      t.expects('getPosition).returns((1.0, 2.0)).times(2).then.
        returns((3.0, 4.0)).times(3)
      
      expect((1.0, 2.0)) { t.getPosition }
      expect((1.0, 2.0)) { t.getPosition }
      expect((3.0, 4.0)) { t.getPosition }
      expect((3.0, 4.0)) { t.getPosition }
      expect((3.0, 4.0)) { t.getPosition }
      intercept[ExpectationException] { t.getPosition }
    }
    
    "fail if an expected method is not called" in {
      val t = mock[Turtle]
      t.expects('penDown)
      intercept[ExpectationException] { verifyExpectations }
    }
    
    "fail if a method is not called enough times" in {
      val t = mock[Turtle]
      t.expects('penDown).times(3)
      t.penDown
      t.penDown
      intercept[ExpectationException] { verifyExpectations }
    }
  }
}
