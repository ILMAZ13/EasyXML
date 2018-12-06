package ru.kpfu.easyxml

import com.github.salomonbrys.kodein.instance
import ru.kpfu.easyxml.modules.entities.NodesResponce
import ru.kpfu.easyxml.modules.network.Api
import ru.kpfu.easyxml.modules.recognition.Recognizer
import ru.kpfu.easyxml.utils.kodein
import java.net.URLDecoder
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val url = "https://www.figma.com/file/nYWXtDGJtn1EXf2ZLjseTS/_KFU_client_test?node-id=0%3A189"
    URLDecoder.decode(url, "UTF-8")?.let {
        val pattern = Pattern.compile("^.*/file/([^/]+)[^?]*\\?[^?]*node-id=([^&]+).*$")
        val matcher = pattern.matcher(it)
        if (matcher.find()) {
            val key = matcher.group(1)
            val id = matcher.group(2)

            kodein.instance<Api>().getRes(token = "4961-2652f564-05e3-4aac-b18b-0be9d2792c35", key = key, ids = id)
                    .subscribe { fileResponse: NodesResponce?, throwable: Throwable? ->
                        fileResponse?.nodes?.get(id)?.document?.let {
                            val screen = kodein.instance<Recognizer>().recognize(it)
                            print(screen)
                        }
                    }
        }
    }
}