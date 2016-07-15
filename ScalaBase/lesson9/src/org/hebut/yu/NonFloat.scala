package org.hebut.yu
import io.Source
object NonFloat extends App{ 
  val source = Source.fromFile("src\\NumberFile.txt").mkString
  val pat1 = """[^((\d+\.){0,1}\d+)^\s+]+$""".r//去掉+试试
  val pat2 = """^((?!^[-]?\d*\.\d+$).)+$""".r
  println("模式1不包含整数:")
  for(token <- source.split("\\s+")){
    for(word <- pat1.findAllIn(token))
      if(!word.equals("")){
        println(token)
      }
  }
  println("模式2包含整数:")
  for(token <- source.split("\\s+")){
    for(word <- pat2.findAllIn(token))
      println(word)
  }
}