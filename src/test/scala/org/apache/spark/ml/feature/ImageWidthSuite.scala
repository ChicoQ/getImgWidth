package org.apache.spark.ml.feature

import org.apache.spark.sql.SparkSession
import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class MyTest extends AnyFunSuite with Matchers with DataFrameSuiteBase {

  private lazy val imageFile = "/Users/chico/IdeaProjects/getImgWidth/data/54893.jpg"

  test("ImageWdith test") {

    implicit val spark: SparkSession = SparkSession.builder()
      .master("local[2]")
      .getOrCreate()

    import spark.implicits._
    val testImg = spark.sparkContext.binaryFiles(imageFile).map{
      case (file, pds) => {
        val dis = pds.open()
        val bytes = Array.ofDim[Byte](1024)
        val all = scala.collection.mutable.ArrayBuffer[Byte]()
        while( dis.read(bytes) != -1) {
          all ++= bytes
        }
      (file, all.toArray) }
    }.toDF("file", "data")
    testImg.printSchema()

    val imgWidth = new ImageWidth()
      .setInputCol("data")
      .setOutputCol("width")
    val outputWidth = imgWidth.transform(testImg)
    outputWidth.printSchema()

    val width = outputWidth
      .select("width")
      .first().getInt(0)

    assert(width === 300)
  }
}