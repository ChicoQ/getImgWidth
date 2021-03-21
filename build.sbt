name := "getImgWidth"

version := "0.1"

scalaVersion := "2.12.13"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.4" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.4.4" % "provided",
  "org.apache.spark" %% "spark-mllib" % "2.4.4" % "provided"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.5" % "test"

libraryDependencies ++= Seq(
"com.holdenkarau" %% "spark-testing-base" % "2.4.4_0.14.0" % "test",
)