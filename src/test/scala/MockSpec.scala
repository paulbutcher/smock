package com.paulbutcher.smock

import org.scalatest.WordSpec

class MockSpec extends WordSpec {
  
  "A mock" should {
    "Fail if any method is called when no expectations are set" in {
      val t = mock[Turtle]
      intercept[ExpectationException] { t.penUp }
    }
  }
}
