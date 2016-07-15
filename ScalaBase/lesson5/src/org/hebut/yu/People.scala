package org.hebut.yu

class People(var age:Int){
  if(age < 0) age=0
}
object People{
  def main(args: Array[String]): Unit = {
    val age1 = 10
    val age2 = -20
    
    println("将Tom的年龄初始化为:"+age1)
    val Tom=new People(age1)
    println("Tom的实际年龄为:"+Tom.age)
    
    println("将Jhon的年龄初始化为:"+age2)
    val Jhon=new People(age2)
    println("Jhon的实际年龄为:"+Jhon.age)
  }
}