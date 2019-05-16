package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class AppBar(document: Document) : View(document) {
    companion object {
        val KEY = "app bar"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        return list
    }
}