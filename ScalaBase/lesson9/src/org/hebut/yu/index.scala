package org.hebut.yu

import java.io.PrintWriter

object index extends App{
  val FileName="Index"
  val path="src\\"+FileName+".txt"
  val out=new PrintWriter(path)
  for (i <- 0 to 20)
    out.println(OutIndex(i))
  out.close
  def OutIndex(n:Int)={
    val value=math.pow(2, n)
    " "*4+value.toInt+" "*(11-value.toString().size)+math.pow(2, -n)
  }
}