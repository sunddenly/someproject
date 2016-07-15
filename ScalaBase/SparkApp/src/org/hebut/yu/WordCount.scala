package org.hebut.yu

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

/**
 * 统计字符出现次数
 */
object WordCount {
  def main(args: Array[String]) {
/*    if (args.length < 1) {
      System.err.println("Usage: <file>")
      System.exit(1)
    }*/

    //val conf = new SparkConf()
    //val conf = new SparkConf().setAppName("Spark Pi").setMaster("spark://hadoop:7077").setJars(List("src\\jar1.jar"))
    //val sc = new SparkContext(conf)
    val conf = new SparkConf().setAppName("Spark Pi").setMaster("spark://hadoop:7077").setJars(List("src\\jar.jar")).setSparkHome("/usr/local/spark")
    val sc = new SparkContext(conf)
    
    val InputPath="hdfs://hadoop:9000/README.md"
    val line = sc.textFile(InputPath)
    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect().foreach(println)
    sc.stop()
  }
}