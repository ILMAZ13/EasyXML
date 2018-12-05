package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class Button(document: Document) : TextView(document) {
    companion object {
        var KEY = "button"
    }

}