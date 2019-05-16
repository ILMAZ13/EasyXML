package ru.kpfu.easyxml.modules.recognition

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.Screen

interface Recognizer {
    fun recognize(document: Document): Screen
}