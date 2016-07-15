package org.hebut.yu
import java.io.File
object NumDir extends App{
  val path = "."
  val dir = new File(path)
  def subdirs(dir:File):Iterator[File]={
    val children = dir.listFiles().filter(_.getName.endsWith("class"))
    children.toIterator ++ dir.listFiles().filter(_.isDirectory).toIterator.flatMap(subdirs _)
  } 
  val n = subdirs(dir).length
  println(n)
}