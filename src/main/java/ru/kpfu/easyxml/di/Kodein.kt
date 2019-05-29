package ru.kpfu.easyxml.di

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.lazy
import com.github.salomonbrys.kodein.provider
import ru.kpfu.easyxml.di.modules.netModule
import ru.kpfu.easyxml.modules.recognition.NameBasedRecognizer
import ru.kpfu.easyxml.modules.recognition.Recognizer

val kodein = Kodein.lazy {
    import(netModule)
    bind<Recognizer>() with provider {
        NameBasedRecognizer()
    }
}