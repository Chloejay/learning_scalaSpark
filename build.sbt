val Scala_212           = "2.12.11"
val sparkVersion        = "2.4.0"
val scalaTestVersion    = "3.1.1"
val scalaCheckVersion   = "1.13.4"

inThisBuild(List(
scalaVersion := Scala_212,
organization := "com.chloejay",
homepage := Some(url("https://github.com/Chloejay/learning_spark")),
developers := List(
Developer(
    "chloe",
    "Chloe Ji",
    "chloejiy@gmail.com",
    url("https://chloejay.github.io/")
)))
)

lazy val minimal = (project in file(".")).
settings(moduleName := "learning_spark_scala",
libraryDependencies ++= Seq(
    "org.apache.spark"  %% "spark-core"      % sparkVersion,
    "org.apache.spark"  %% "spark-streaming" % sparkVersion,
    "org.apache.spark"  %% "spark-sql"       % sparkVersion,
    "org.scalatest"    %% "scalatest"        % scalaTestVersion  % "test",
    "org.scalacheck"   %% "scalacheck"       % scalaCheckVersion % "test"
    )
)