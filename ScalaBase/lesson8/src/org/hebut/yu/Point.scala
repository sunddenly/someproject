package org.hebut.yu

class Point(val x:Double,val y:Double) {
  override def toString="x= "+x+" y= "+y
}
class LabelPoint(val label:String,override val x:Double,override val y:Double)extends Point(x,y){
  override def toString ="label= "+label+"x= "+x+"y= "+y
}
object PointTest{
  def main(args: Array[String]): Unit = {
    val point=new Point(2,3)
    val lpoint=new LabelPoint("圆形 ",2,3)
    println(point)
    println(lpoint)
  }
}