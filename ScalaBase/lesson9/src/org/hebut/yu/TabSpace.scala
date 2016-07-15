package org.hebut.yu

import scala.io.Source
import java.io.PrintWriter

object TabSpace extends App{
  val FileName="TabSpace"
  val path="src\\"+FileName+".txt"
  val linesIterator=Source.fromFile(path).getLines()
  lazy val TabIterator=Source.fromFile(path).getLines()
  val linesRecord=linesIterator.toArray
  lazy val pw=new PrintWriter(path)
  println(FileName+"文件内容如下:")
  linesRecord.foreach(println)
  linesRecord.foreach {
    line =>pw.write(line.replaceAll("\t", " ")+"\n")
  }
  pw.close
  println("替换后"+FileName+"文件内容如下:")
  TabIterator.foreach(println)
  
}