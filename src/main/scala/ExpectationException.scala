package com.paulbutcher.smock

case class ExpectationException(msg: String) extends RuntimeException(msg)
