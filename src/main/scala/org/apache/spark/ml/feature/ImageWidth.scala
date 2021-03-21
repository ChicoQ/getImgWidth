package org.apache.spark.ml.feature

import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import org.apache.spark.ml.UnaryTransformer
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.util.{DefaultParamsReadable, Identifiable}
import org.apache.spark.sql.types._


/**
 * The program is to create Spark ML transformer which will return the width of a given image.
 * Input data – image in binary format (Array[Byte] type)
 * Output - width of image (Int type)
 */
class ImageWidth(override val uid: String)
  extends UnaryTransformer[Array[Byte], Int, ImageWidth] {

  def this() = this(Identifiable.randomUID("ImageWidth"))

  override protected def createTransformFunc: Array[Byte] => Int = {
    getImageWidth
  }

  private def getImageWidth(bytes: Array[Byte]): Int = {
    val img = ImageIO.read(new ByteArrayInputStream(bytes))
    if (img == null) 0 else img.getWidth
  }

  override protected def validateInputType(inputType: DataType): Unit = {
    require(inputType == BinaryType,
      s"Input type must be Array[Byte] but got ${inputType.catalogString}.")
  }

  override protected def outputDataType: DataType = IntegerType

  override def copy(extra: ParamMap): ImageWidth = defaultCopy(extra)

}

object ImageWidth extends DefaultParamsReadable[ImageWidth] {

  override def load(path: String): ImageWidth = super.load(path)

}
