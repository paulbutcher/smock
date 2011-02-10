package com.paulbutcher.smock

case class ExpectationException(msg: String, cause: Throwable = null) extends RuntimeException(msg, cause)
