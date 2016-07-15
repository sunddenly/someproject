package org.hebut.yu

object PokerFace extends Enumeration {
  type PokerFace=Value
  val SPADES=Value("♠")
  val HEARTS=Value("♥")
  val DIAMONDS=Value("♢")
  val CLUBS=Value("♧")
  def main(args: Array[String]): Unit = {
    for(poker <- PokerFace.values)
      println(poker)
  }
}
