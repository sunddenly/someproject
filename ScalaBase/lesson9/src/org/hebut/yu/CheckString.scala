package org.hebut.yu

object CheckString extends App{
  val FileName="CheckString"
  val path="src\\"+FileName+".txt"
  println(FileName+"文件中长度大于12的字符串为:")
  io.Source.fromFile(path).mkString.split("\\s+").foreach (str => if(str.length()>12) println(str))
}