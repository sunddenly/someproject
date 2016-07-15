package org.hebut.yu

class BankAccount {
  private var balance=0.0
  def deposit(depamount:Double){
    balance+=depamount
  }
  def withdraw(drawamount:Double){
    balance-=drawamount
  }
  def current=balance
}
object BankAccount{
  def main(args: Array[String]): Unit = {
    val Drawamount=800
    val Depamount=1000
    val acc=new BankAccount
    println("存入金额:"+Depamount)
    acc.deposit(Depamount)
    println("余额:"+acc.current)
    println("取出金额:"+Drawamount)
    acc.withdraw(Drawamount)
    println("余额为:"+acc.current)
  }
}