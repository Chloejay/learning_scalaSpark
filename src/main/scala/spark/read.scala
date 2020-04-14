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

    println("===============")
    
    val rdd= sc.textFile("src/main/scala/data.txt").map(line => line.toUpperCase)
    rdd.cache //persist 
    val containsSoundness = rdd.filter(line => line.contains("SOUNDNESS"))
    val array= containsSoundness.collect() 

    array.take(2).foreach(println) 
  
    sc.stop()
  }
}
