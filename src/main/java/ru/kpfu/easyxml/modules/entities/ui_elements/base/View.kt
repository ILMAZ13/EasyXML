package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.Color
import ru.kpfu.easyxml.modules.entities.Document

open class View(var document: Document) {
    var backgroundColor: Color? = null

    init {
        backgroundColor = document.backgroundColor
    }
}