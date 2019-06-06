package ru.kpfu.easyxml

import com.github.salomonbrys.kodein.instance
import ru.kpfu.easyxml.di.kodein
import ru.kpfu.easyxml.modules.network.Api
import ru.kpfu.easyxml.modules.recognition.ConstraintGenerator
import ru.kpfu.easyxml.modules.recognition.ObjectDetector
import ru.kpfu.easyxml.modules.recognition.Recognizer
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.net.URLDecoder
import java.nio.channels.Channels
import java.util.regex.Pattern

fun main() {
//    val url = "https://www.figma.com/file/gRMfq4AUKyimENMXkJ7abt/Google-Material-Design?node-id=0:1714"
    val url = "https://www.figma.com/file/d6fDGDphwkZyVH5VIs3BOOrD/Untitled?node-id=1%3A2"
    URLDecoder.decode(url, "UTF-8")?.let {
        val pattern = Pattern.compile("^.*/file/([^/]+)[^?]*\\?[^?]*node-id=([^&]+).*$")
        val matcher = pattern.matcher(it)
        if (matcher.find()) {
            val key = matcher.group(1)
            val id = matcher.group(2)
            val token = "4961-2652f564-05e3-4aac-b18b-0be9d2792c35"

            val api by kodein.instance<Api>()
            val recognizer by kodein.instance<Recognizer>()
            val objectDetector by kodein.instance<ObjectDetector>()

            api.getRes(token, key, id).subscribe { fileResponse, _ ->
                fileResponse?.nodes?.get(id)?.document?.let { doc ->
                    api.getImages(token, key, doc.id, "jpg").subscribe { imageResponse, _ ->
                        imageResponse?.let {
                            val url = URL(it.images[doc.id])
                            val file = File("last.jpg")

                            downloadImage(url, file)

                            val results = objectDetector.recognize(file)

                            val constraintGenerator = ConstraintGenerator()

                            constraintGenerator.fixConstraints(doc)

                            val screen = recognizer.recognize(doc, results)

                            constraintGenerator.generateConstraint(screen)

                            print(screen.getString())
                        }
                    }
                }
            }
        }
    }
}

private fun downloadImage(url: URL, file: File) {
    val channel = Channels.newChannel(url.openStream())
    val outputStream = FileOutputStream(file)
    outputStream.channel.transferFrom(channel, 0, Long.MAX_VALUE)
}