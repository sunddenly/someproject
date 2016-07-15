package org.hebut.yu

import scala.io.StdIn

object Conversions {
  private val i2c=30.48
  private val g2l=3.785411784
  private val m2k=1.609344
  def inchesToCentimeters(inch:Double):Double={
    inch*i2c
  }
  def gallonsToLiters(gallon:Double):Double={
    gallon*g2l
  }
  def milesTokilometers(mile:Double):Double={
    mile*m2k
  }
  def main(args: Array[String]): Unit = {
    val inch=15
    val gallon=15
    val mile=15
    println(inch+"英尺= "+inchesToCentimeters(inch))
    println(gallon+"加仑= "+gallonsToLiters(gallon))
    println(mile+"公里= "+milesTokilometers(mile))
  }
}
