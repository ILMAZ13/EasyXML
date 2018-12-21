package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class Icon(document: Document) : View(document) {
    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<ImageView")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }
}