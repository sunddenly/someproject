package org.hebut.yu

object Card extends Enumeration with App{
  val SPADES=Value("♠")
  val HEARTS=Value("♥")
  val DIAMONDS=Value("♢")
  val CLUBS=Value("♧")
  type Card=Value
  def color(c:Card)={
    if(c==Card.HEARTS||c==Card.DIAMONDS)
      println("Red")
     else
       println("Black")
  }
  color(SPADES)
  color(DIAMONDS)
}