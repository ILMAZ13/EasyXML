package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class NavBar(document: Document) : View(document) {
    companion object {
        const val KEY = "nav_bar"
    }

    override fun getPrefix() = "nb"

    override fun isShown() = false

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        return list
    }
}
