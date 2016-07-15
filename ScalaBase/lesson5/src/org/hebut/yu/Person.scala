package org.hebut.yu

class Person(val name:String) {
  private val namearr=name.split(" ")
  def FirstName=namearr(0)
  def LastName=namearr(1)
}
object Person{
  def main(args: Array[String]): Unit = {
    val person=new Person("Xinyu Jiang")
    //name参数自动转为私有字段,并生成公有getter
    println("person的名称为:"+person.name)
    println("person的FisrtName:"+person.FirstName)
    println("person的LastName:"+person.LastName)
  }
}