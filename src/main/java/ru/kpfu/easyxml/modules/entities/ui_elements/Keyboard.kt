package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class Keyboard(document: Document) : View(document) {
    companion object {
        const val KEY = "keyboard"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        return list
    }
}
