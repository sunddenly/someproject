package random

object Test extends App{
  var r =random.Random
  r.setSeed(args(0).toInt)
  for(i <- 1 to 10) println(r.nextInt())
  for(i <- 1 to 10) println(r.nextDouble())
}