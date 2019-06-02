package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Color
import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class CheckBox(document: Document) : View(document) {
    companion object {
        const val KEY = "checkbox"
    }

    override fun getPrefix() = "cb"

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<androidx.appcompat.widget.AppCompatCheckBox")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }

    override fun addBackground(list: MutableList<String>, it: Color, opacity: Float) {
        list.add("app:buttonTint=\"${it.getHex(opacity)}\"")
    }
}