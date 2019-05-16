package ru.kpfu.easyxml

import com.github.salomonbrys.kodein.instance
import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.figma.NodesResponse
import ru.kpfu.easyxml.modules.network.Api
import ru.kpfu.easyxml.modules.recognition.Recognizer
import ru.kpfu.easyxml.utils.kodein
import java.io.FileOutputStream
import java.net.URL
import java.net.URLDecoder
import java.nio.channels.Channels
import java.util.regex.Pattern

fun main() {
//    val url = "https://www.figma.com/file/gRMfq4AUKyimENMXkJ7abt/Google-Material-Design?node-id=0:1714"
    val url = "https://www.figma.com/file/g8yTzPXmYsX4B8RcbtNmPcGH/zero-online-store?node-id=0%3A525"
    URLDecoder.decode(url, "UTF-8")?.let {
        val pattern = Pattern.compile("^.*/file/([^/]+)[^?]*\\?[^?]*node-id=([^&]+).*$")
        val matcher = pattern.matcher(it)
        if (matcher.find()) {
            val key = matcher.group(1)
            val id = matcher.group(2)
            val token = "4961-2652f564-05e3-4aac-b18b-0be9d2792c35"

//            kodein.instance<Api>().getImages(token = "4961-2652f564-05e3-4aac-b18b-0be9d2792c35", key = key, ids = "I1264:19831;876:766")
//                    .subscribe { imageResponse, throwable ->
//                        run {
//                            print(imageResponse)
//                        }
//                    }

            kodein.instance<Api>().getRes(token, key, id)
                    .subscribe { fileResponse: NodesResponse?, throwable: Throwable? ->
                        fileResponse?.nodes?.get(id)?.document?.let {
                            kodein.instance<Api>().getImages(token, key, getImageIds(it))
                                    .subscribe { imageResponse, throwable ->
                                        imageResponse?.let {
                                            downloadImages(it.images)
                                        }
                                    }

                            val screen = kodein.instance<Recognizer>().recognize(it)
                            print(screen.getString())
                        }
                    }
        }
    }
}

private fun downloadImages(images: Map<String, String?>) {
    images.entries.forEach {
        it.value?.let { link ->
            downloadImage(URL(link), it.key)
        }
    }
}

private fun downloadImage(url: URL, name: String) {
    val channel = Channels.newChannel(url.openStream())
    val outputStream = FileOutputStream("$name.png")
    outputStream.channel.transferFrom(channel, 0, Long.MAX_VALUE)
}

private fun getImageIds(document: Document): String {
    val idsList = mutableListOf<String>()
    isImage(document, idsList)
    val string = StringBuilder()
    idsList.forEachIndexed { index, s ->
        if (index != 0) string.append(",")
        string.append(s)
    }
    return string.toString()
}

private fun isImage(document: Document, ids: MutableList<String>): Boolean {
    document.isImage = document.children?.let { list ->
        var allChildIsImage = true
        list.forEach {
            allChildIsImage = isImage(it, ids) and allChildIsImage
        }
        if (!allChildIsImage) {
            list.forEach {
                if (it.isImage && it.visible) ids.add(it.id)
            }
        }
        allChildIsImage
    } ?: document.isImageType()
    return document.isImage
}