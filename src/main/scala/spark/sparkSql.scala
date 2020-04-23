package spark 

import org.apache.spark.sql.{DataFrame, SparkSession, Row}
import org.apache.spark.sql.catalyst.expressions.aggregate._
import org.apache.spark.sql.expressions._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SQLContext
import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.DoubleType


object MainSQL {
// data struct 
case class Person(name: String, age: Long)
case class Occupation(occ: String, id: Array[Person])


def main(args: Array[String]) {
val spark = SparkSession
  .builder()
  .appName("SparkSQL")
  .config("spark.master", "local")
  .getOrCreate()

import spark.implicits._ 
createDf(spark) 
runSQL(spark) 
}

private def createDf(spark: SparkSession): Unit = {
  import spark.implicits._ 

  val ppl = Person("emma", 26)
  val occ = Occupation("student", Array(ppl))  
  val df = spark.createDataFrame(Seq(occ))
  df.show()
  df.printSchema()
}

private def runSQL (spark: SparkSession): Unit = {
  import spark.implicits._ 
  val df = spark.read.json("src/main/scala/spark/resources/nyt2.json") 

  df.select($"publisher").distinct().show()
  df.groupBy($"publisher").count.show() 
// +--------------------+-----+
// |           publisher|count|
// +--------------------+-----+
// |                 DAW|   16|
// |                 HQN|    7|
// |           Doubleday|  475|
// |                 Roc|   39|
// |Mullholland Books...|   12|
// |  Marian Wood/Putnam|   34|
// |       Gallery/Scout|    7|
// |Harper Voyager/Ha...|    1|
// |            Pantheon|   39|
// |         Quirk Books|   16|
// |               Forge|   13|
// |           Algonquin|   15|
// |             Viking,|    1|
// |               Voice|    7|
// |        St. Martin‘s|    2|
// |             Del Rey|   65|
// |       Thomas Nelson|    1|
// |   Eos/HarperCollins|    4|
// |                 Ace|  125|
// |       HarperCollins|    2|
// +--------------------+-----+
// only showing top 20 rows

  df.printSchema()
  import org.apache.spark.sql.types.DoubleType
  val df2 = df.selectExpr("Cast (price AS DOUBLE) price ")

  // temp view 
  df2.createOrReplaceTempView("nyBooks")
  val sqlDF = spark.sql("""
  SELECT author, SUM(price) 
  FROM nyBooks 
  GROUP BY author 
  ORDER BY author 
  LIMIT 10""") 

  sqlDF.show() 
}
}