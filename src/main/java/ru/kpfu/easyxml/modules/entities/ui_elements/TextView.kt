package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.Document
import ru.kpfu.easyxml.modules.entities.TypeStyle
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

open class TextView(document: Document) : View(document) {
    var text: String = ""
    var textStyle: TypeStyle? = null

    init {
        text = document.characters ?: ""
        textStyle = document.style
    }
}