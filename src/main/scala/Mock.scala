package com.paulbutcher.smock

trait Mock {
  def receives(name: Symbol)(expectations: Expectations): Expectation = {
    val expectation = new Expectation(this, name)
    expectations.add(expectation)
    expectation
  }
}
