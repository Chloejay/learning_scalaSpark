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

lazy val sparkScala = (project in file(".")).
settings(moduleName := "learning_spark_scala",
libraryDependencies ++= Seq(
    "org.apache.spark"  %% "spark-core"      % sparkVersion % "provided",
    "org.apache.spark"  %% "spark-streaming" % sparkVersion % "provided",
    "org.apache.spark"  %% "spark-sql"       % sparkVersion % "provided",
    "org.scalatest"    %% "scalatest"        % scalaTestVersion  % "test",
    "org.scalacheck"   %% "scalacheck"       % scalaCheckVersion % "test",
    "org.apache.spark" %% "spark-core" % "0.8.0-incubating" % "provided",
    ),
assemblyJarName in assembly := "sparkScala.jar"
)

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}