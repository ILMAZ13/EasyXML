package ru.kpfu.easyxml.utils

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.provider
import com.github.salomonbrys.kodein.singleton
import ru.kpfu.easyxml.modules.network.Api
import ru.kpfu.easyxml.modules.network.NetworkModule
import ru.kpfu.easyxml.modules.recognition.NameBasedRecognizer
import ru.kpfu.easyxml.modules.recognition.Recognizer

val kodein = Kodein {
    bind<Api>() with singleton {
        NetworkModule.getApi()
    }
    bind<Recognizer>() with provider {
        NameBasedRecognizer()
    }
}