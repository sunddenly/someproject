package org.hebut.yu

abstract class UnitConversion {
  def Converse(from:Double):Double
}
object inchesToCentimeters extends UnitConversion{
  private val i2c=30.48
  override def Converse(inche:Double):Double={
    inche*i2c
  }
}
object gallonsToLiters extends UnitConversion{
  private val g2l=3.785311784
  override def Converse(gallon:Double):Double={
    g2l*gallon
  }
}
object milesToKilometers extends UnitConversion{
  private val m2k=1.609344
  override def Converse(mile:Double):Double={
    m2k*mile
  }
}
object Test{
  def main(args: Array[String]): Unit = {
    val inche=10; val gallon=10; val mile=10
    println(inche+"英尺= "+inchesToCentimeters.Converse(inche)+" 厘米")
    println(gallon+"加仑= "+gallonsToLiters.Converse(gallon)+" 升")
    println(mile+"公里= "+milesToKilometers.Converse(mile)+" 公里")
  }
}


