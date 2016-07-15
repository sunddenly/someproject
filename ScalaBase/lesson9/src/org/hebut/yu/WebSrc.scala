package org.hebut.yu

object WebSrc extends App{
  val pat = """<img.*?src=["'](.+?)["'].*?>""".r
  for (pat(src) <-pat.findAllIn(io.Source.fromURL("http://www.baidu.com").mkString)) {
    println(src)
  }
}