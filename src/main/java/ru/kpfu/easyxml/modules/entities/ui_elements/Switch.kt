package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class Switch(document: Document) : View(document) {
    companion object {
        const val KEY = "switch"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<Switch")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }
}
