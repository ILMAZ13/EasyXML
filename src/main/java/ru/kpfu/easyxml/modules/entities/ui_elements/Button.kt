package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document

class Button(document: Document) : TextView(document) {
    companion object {
        const val KEY = "button"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<Button")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }
}