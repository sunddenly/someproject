package org.hebut.yu.two
import java.awt.Point
import java.awt.Rectangle

class Squre extends Rectangle{
  height=0
  width=0
  x=0
  y=0
  def this(p:Point,w:Int){
    this()
    this.height=w
    this.width=w
    this.x=p.x
    this.y=p.y
  }
  def this(width:Int){
    this(new Point(0,0),width)
  }
}
object SqureTest {
  def main(args: Array[String]): Unit = {
    val rect1=new Squre()
    val rect2=new Squre(2)
    val rect3=new Squre(new Point(2,3),5)
    println(rect1)
    println(rect2)
    println(rect3)
  }
}