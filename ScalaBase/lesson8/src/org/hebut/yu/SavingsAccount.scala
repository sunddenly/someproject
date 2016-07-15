package org.hebut.yu

class SavingsAccount(initialBalance:Double) extends BankAccount(initialBalance){
  private var freeCount=3
  private val interestRate=0.03
  def CurrentCount = freeCount
  def earnMonthlyInterrest:Double={
    freeCount=3
    super.deposit(super.deposit(0)*interestRate)
    super.deposit(0)*interestRate
  }
  override def deposit(amount:Double):Double={
    if(freeCount>0){
      freeCount-=1
      super.deposit(amount)
    }else{
      super.deposit(amount-1)
    }
  }
  override def withdraw(amount:Double):Double={
    if(freeCount>0){
      freeCount-=1
      super.withdraw(amount)
    }else{
      super.withdraw(amount+1)
    }
  }
}
object SaveTest{
  val dbal=1000
  val wbal=100
  var interest=0.0
  val sa=new SavingsAccount(1000)
  def main(args: Array[String]): Unit = {
    for(i<- 1 to 32){
      if(i>=1&& i<=4){
        sa.deposit(1000)
        println(i+"号存入: "+dbal+"余额: "+sa.currentBalance+" 剩余免费次数: "+sa.CurrentCount)
      }else if(i>=29&&i<=31){
        if(i==30)
          interest=sa.earnMonthlyInterrest
        sa.withdraw(100)
        println(i+"号取出: "+wbal+"余额: "+sa.currentBalance+" 剩余免费次数: "+sa.CurrentCount)
        
      }
    }
    println("一个月的利息为: "+interest+" 剩余免费次数: "+sa.CurrentCount)
  }
}