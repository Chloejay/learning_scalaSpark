package spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._


object Agg{
  def main(args:Array[String]){
    val conf = new SparkConf().setAppName("scala").setMaster("local[2]")
    val sc = new SparkContext(conf)
    //TODO aggregation 

    val rdd = sc.textFile("???")
    println(rdd) 

}
}

