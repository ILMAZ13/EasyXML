package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class CheckBox(document: Document) : View(document) {
    companion object {
        val KEY = "check"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<CheckBox")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }
}