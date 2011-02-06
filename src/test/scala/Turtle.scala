package com.paulbutcher.smock

trait Turtle {
  def penUp()
  def penDown()
  def forward(distance: Double): (Double, Double)
  def turn(angle: Double): Double
  def getAngle: Double
  def getPosition(): (Double, Double)
  def setPosition(x: Double, y: Double): (Double, Double)
}
