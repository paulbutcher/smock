package com.paulbutcher.smock

class RealTurtle extends Turtle {
  def penUp() { println("penUp") }
  def penDown() { println("penUp") }
  def forward(distance: Double) { println("forward: "+ distance) }
  def turn(angle: Double) { println("turn: "+ angle) }
  def getPosition() = (1.0, 2.0)
}
