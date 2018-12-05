package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

open class TextView(document: Document) : View(document) {
    var text: String? = null

}