package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class CardView(document: Document) : ViewGroup(document) {
    companion object {
        val KEY = "card"
    }

    var cardCornerRadius: Double? = null
}