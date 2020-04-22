package spark 

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD

/**
  * set up spark to initialize SparkContext 
  *
  */

object Main {
  def main(args: Array[String]) {
    //Setup;  
    val conf = new SparkConf().setAppName("scala").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val file = "src/main/scala/spark/data.txt"
    val outputFile = "src/main/scala/spark/output"

    try{
    val rdd = sc.textFile(file).map(line => line.toUpperCase)
    // rdd.cache //persist 
    val containsSoundness = rdd.filter(line => line.contains("SOUNDNESS"))
    // val array = containsSoundness.collect() 

    val words = rdd.flatMap(line => line.split("""[^\p{IsAlphabetic}]+"""))
    .map(word => (word,1))
    .reduceByKey(_ + _)

    def peek(rdd: RDD[_], n: Int):Unit = {
      rdd.take(n).foreach(println)
    }
    //  peek(containsSoundness,2)
     peek(words, 20)
     words.saveAsTextFile(outputFile)
        }finally sc.stop()
      }
}