package ru.kpfu.easyxml.di

import com.github.salomonbrys.kodein.*
import ru.kpfu.easyxml.di.modules.netModule
import ru.kpfu.easyxml.modules.recognition.NameBasedRecognizer
import ru.kpfu.easyxml.modules.recognition.ObjectDetector
import ru.kpfu.easyxml.modules.recognition.Recognizer

val kodein = Kodein.lazy {
    import(netModule)
    bind<Recognizer>() with provider {
        NameBasedRecognizer()
    }
    bind<ObjectDetector>() with singleton { ObjectDetector() }
}