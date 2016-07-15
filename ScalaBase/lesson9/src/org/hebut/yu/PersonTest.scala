package org.hebut.yu

import collection.mutable.ArrayBuffer
import java.io.{ObjectInputStream, FileOutputStream, FileInputStream, ObjectOutputStream}

class Person(var name:String) extends Serializable{
  val friends = new ArrayBuffer[Person]()
  def addFriend(friend : Person){
    friends += friend
  }
  override def toString() = {
    var str = "My name is " + name + " and my friends name is "
    friends.foreach(str += _.name + ",")
    str
  }
}

object PersonTest extends App{
  val p1 = new Person("JackChen")
  val p2 = new Person("Jhon·D")
  val p3 = new Person("Sunday")
  p1.addFriend(p2)
  p1.addFriend(p3)
  println(p1)
  val out = new ObjectOutputStream(new FileOutputStream("src\\Person.txt"))
  out.writeObject(p1)
  out.close()
  val in =  new ObjectInputStream(new FileInputStream("src\\Person.txt"))
  val p = in.readObject().asInstanceOf[Person]
  println(p)
}