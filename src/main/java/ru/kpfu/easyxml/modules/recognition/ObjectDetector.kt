package ru.kpfu.easyxml.modules.recognition

import com.google.protobuf.TextFormat
import object_detection.protos.StringIntLabelMapOuterClass
import org.tensorflow.SavedModelBundle
import org.tensorflow.Tensor
import org.tensorflow.framework.MetaGraphDef
import org.tensorflow.types.UInt8
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO

class ObjectDetector {

    companion object {
        const val LABELS_PATH = "labels/labelmap.pbtxt"
        const val MODEL_PATH = "models/inference_graph_16k/saved_model"
        const val MIN_SCORE = 0.5

    }

    fun recognize(imageFile: File): List<Result> {
        val results = mutableListOf<Result>()
        val labels = loadLabels()
        SavedModelBundle.load(MODEL_PATH, "serve").use { model ->
            printSignature(model)

            makeImageTensor(imageFile).use { input ->
                val outputs = model
                        .session()
                        .runner()
                        .feed("image_tensor", input)
                        .fetch("detection_scores")
                        .fetch("detection_classes")
                        .fetch("detection_boxes")
                        .run()
                outputs[0].let { scoresT ->
                    outputs[1].let { classesT ->
                        outputs[2].let { boxesT ->
                            // All these tensors have:
                            // - 1 as the first dimension
                            // - maxObjects as the second dimension
                            // While boxesT will have 4 as the third dimension (2 sets of (x, y) coordinates).
                            // This can be verified by looking at scoresT.shape() etc.
                            val maxObjects = scoresT.shape()[1].toInt()
                            val scores = scoresT.copyTo(Array(1) { FloatArray(maxObjects) })[0]
                            val classes = classesT.copyTo(Array(1) { FloatArray(maxObjects) })[0]
                            val boxes = boxesT.copyTo(Array(1) { Array(maxObjects) { FloatArray(4) } })[0]

                            //todo for test only, remove then
                            val img = ImageIO.read(imageFile)
                            val g2d = img.createGraphics()
                            g2d.color = Color.RED
                            g2d.font = Font("TimesRoman", Font.BOLD, 15)

                            val width = img.width
                            val height = img.height
                            for (i in scores.indices) {
                                if (scores[i] < MIN_SCORE) {
                                    continue
                                }
                                System.out.printf("\tFound %-20s (score: %.4f)\n", labels[classes[i].toInt()], scores[i])
                                val x = Math.round(width * boxes[i][1])
                                val y = Math.round(height * boxes[i][0])
                                val x2 = Math.round(width * boxes[i][3])
                                val y2 = Math.round(height * boxes[i][2])
                                results.add(Result(labels[classes[i].toInt()] ?: "?",
                                        boxes[i][1],
                                        boxes[i][0],
                                        boxes[i][3],
                                        boxes[i][2]))

                                //todo to removing
                                g2d.drawRect(x, y, x2 - x, y2 - y)
                                g2d.drawString(labels[classes[i].toInt()], x, y + 15)

                            }
                            g2d.dispose()
                            ImageIO.write(img, "jpg", File("last_result.jpg"))
                        }
                    }
                }
            }
        }
        return results
    }

    @Throws(Exception::class)
    private fun loadLabels(): Array<String?> {
        val text = String(Files.readAllBytes(Paths.get(LABELS_PATH)), StandardCharsets.UTF_8)
        val builder = StringIntLabelMapOuterClass.StringIntLabelMap.newBuilder()
        TextFormat.merge(text, builder)
        val proto = builder.build()
        var maxId = 0
        for (item in proto.itemList) {
            if (item.id > maxId) {
                maxId = item.id
            }
        }
        val ret = arrayOfNulls<String>(maxId + 1)
        for (item in proto.itemList) {
            ret[item.id] = item.name
        }
        return ret
    }

    @Throws(IOException::class)
    private fun makeImageTensor(file: File): Tensor<UInt8> {
        val img = ImageIO.read(file)
        if (img.type != BufferedImage.TYPE_3BYTE_BGR) {
            throw IOException(
                    String.format(
                            "Expected 3-byte BGR encoding in BufferedImage, found %d (file: %s). This code could be made more robust",
                            img.type, file.name))
        }
        val data = (img.data.dataBuffer as DataBufferByte).data
        // ImageIO.read seems to produce BGR-encoded images, but the model expects RGB.
        bgr2rgb(data)
        val BATCH_SIZE: Long = 1
        val CHANNELS: Long = 3
        val shape = longArrayOf(BATCH_SIZE, img.height.toLong(), img.width.toLong(), CHANNELS)
        return Tensor.create(UInt8::class.java, shape, ByteBuffer.wrap(data))
    }

    @Throws(Exception::class)
    private fun printSignature(model: SavedModelBundle) {
        val m = MetaGraphDef.parseFrom(model.metaGraphDef())
        val sig = m.getSignatureDefOrThrow("serving_default")
        val numInputs = sig.inputsCount
        var i = 1
        println("MODEL SIGNATURE")
        println("Inputs:")
        for ((key, t) in sig.inputsMap) {
            System.out.printf(
                    "%d of %d: %-20s (Node name in graph: %-20s, type: %s)\n",
                    i++, numInputs, key, t.name, t.dtype)
        }
        val numOutputs = sig.outputsCount
        i = 1
        println("Outputs:")
        for ((key, t) in sig.outputsMap) {
            System.out.printf(
                    "%d of %d: %-20s (Node name in graph: %-20s, type: %s)\n",
                    i++, numOutputs, key, t.name, t.dtype)
        }
        println("-----------------------------------------------")
    }

    private fun bgr2rgb(data: ByteArray) {
        var i = 0
        while (i < data.size) {
            val tmp = data[i]
            data[i] = data[i + 2]
            data[i + 2] = tmp
            i += 3
        }
    }

    data class Result(
            val name: String,
            val x: Float,
            val y: Float,
            val x2: Float,
            val y2: Float
    )
}