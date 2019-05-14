package ru.kpfu.easyxml

import com.github.salomonbrys.kodein.instance
import ru.kpfu.easyxml.modules.network.Api
import ru.kpfu.easyxml.utils.kodein
import java.net.URLDecoder
import java.util.regex.Pattern

fun main() {
    val url = "https://www.figma.com/file/ei0niJP4wU4Ez34TyqqKx5/Android-Design-System-Demo-(Copy)?node-id=1271%3A778"
    URLDecoder.decode(url, "UTF-8")?.let {
        val pattern = Pattern.compile("^.*/file/([^/]+)[^?]*\\?[^?]*node-id=([^&]+).*$")
        val matcher = pattern.matcher(it)
        if (matcher.find()) {
            val key = matcher.group(1)
            val id = matcher.group(2)

            kodein.instance<Api>().getImages(token = "4961-2652f564-05e3-4aac-b18b-0be9d2792c35", key = key, ids = "I1264:19831;876:766")
                    .subscribe { imageResponse, throwable ->
                        run {
                            print(imageResponse)
                        }
                    }

//            kodein.instance<Api>().getRes(token = "4961-2652f564-05e3-4aac-b18b-0be9d2792c35", key = key, ids = id)
//                    .subscribe { fileResponse: NodesResponse?, throwable: Throwable? ->
//                        fileResponse?.nodes?.get(id)?.document?.let {
//                            val screen = kodein.instance<Recognizer>().recognize(it)
//                            print(screen.getString())
//                        }
//                    }
        }
    }
}