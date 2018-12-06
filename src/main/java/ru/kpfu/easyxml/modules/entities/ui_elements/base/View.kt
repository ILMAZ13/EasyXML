package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.Color
import ru.kpfu.easyxml.modules.entities.Document

open class View(var document: Document) {
    var name: String = ""
    var backgroundColor: Color? = null
    var radius: Double = 0.0

    init {
        backgroundColor = document.backgroundColor
        name = document.name ?: ""
    }
}