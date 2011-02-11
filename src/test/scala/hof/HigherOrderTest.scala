package com.paulbutcher.smock.example

import org.scalatest.WordSpec
import com.paulbutcher.smock.MockFactory

class HigherOrderTest extends WordSpec with MockFactory {
  def repeat(n: Int)(what: => Unit) =
    for(x <- 0 until n)
      what

  "repeat" should {
    "repeat 10 times" in {
      val f = mock[Function0[Unit]]
      f.expects(Symbol("apply$mcV$sp")).times(10)
      repeat(10) { f() }
    }
  }
}
