package org.hebut.yu

object ReadNumber extends App{
  val pattern="(\\d+[.]\\d+)".r
  val pattern1="^\\d+(\\.\\d+)?".r
  val pattern2="[0-9]+(\\.\\d+)?".r
  val FileName="NumberFile"
  val path = "src\\"+FileName+".txt"
  val FileStr=io.Source.fromFile(path).mkString
  val StrArray=pattern2.findAllIn(FileStr).toArray
  var total=0d
  val len=StrArray.length
  StrArray.foreach (total +=_.toDouble)
  println("文本中浮点数总和: "+total)
  println("文本中浮点数平均数: "+total/len+len)
  println("文本中浮点数的最大值: "+StrArray.max)
  println("文本中浮点数的最大值: "+StrArray.min)
}