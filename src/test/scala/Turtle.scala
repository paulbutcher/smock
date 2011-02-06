package com.paulbutcher.smock

trait Turtle {
  def penUp()
  def penDown()
  def forward(distance: Double)
  def turn(angle: Double)
  def getPosition(): (Double, Double)
  def setPosition(x: Double, y: Double)
}
