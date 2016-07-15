package org.hebut.yu

import scala.io.Source
import java.io.PrintWriter

object ReverseLines extends App {
  val filename="File.txt"
  val RefileName="ReverseFile.txt"
  
  val source=Source.fromFile("src\\"+filename)
  lazy val ReSource=Source.fromFile("src\\"+RefileName)
  lazy val pw = new PrintWriter("src\\"+RefileName)
  
  val linesIterator=source.getLines()
  val linesRecord=linesIterator.toArray
  val reverseRecord=linesRecord.reverse
  
  reverseRecord.foreach {
    line =>pw.write(line+"\n")
  }
  pw.close()
  
  println(filename+"文件内容如下:")
  linesRecord.foreach (line=>println(line))
 
  println(RefileName+"文件内容如下:")
  ReSource.getLines().foreach(line=>println(line))
}