package org.hebut.yu

class Counter {
  private var value=100
  def increment(){
    if(value<Int.MaxValue)
      value+=1
    else
      value
  }
  def current=value
  
}
object Counter{
  def main(args: Array[String]): Unit = {
    val max=Int.MaxValue
    println("Int类型的最大值:"+max)
    val counter=new Counter
    for(i <- 1 to (max))
      counter.increment()
    println("经过"+max+"增加后Value值为:"+counter.current)
  }
}