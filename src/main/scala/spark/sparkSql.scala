package spark 

import org.apache.spark.sql.{Dataset, DataFrame, SparkSession, Row}
import org.apache.spark.sql.catalyst.expressions.aggregate._
import org.apache.spark.sql.expressions._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SQLContext
import org.apache.spark._
import org.apache.spark.rdd.RDD

object HappyPandas {

   def sparkSession(): SparkSession = {
   val session = SparkSession.builder()
       .enableHiveSupport()
       .getOrCreate()
   
   import session.implicits._
   session
   }
}


