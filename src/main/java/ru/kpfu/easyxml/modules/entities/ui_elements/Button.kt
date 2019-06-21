package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Color
import ru.kpfu.easyxml.modules.entities.figma.Document

class Button(document: Document) : TextView(document) {
    companion object {
        const val KEY = "button"
    }

    init {
        textColor = null
    }

    override fun getPrefix() = "btn"

    override fun isShown() = true

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<androidx.appcompat.widget.AppCompatButton")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }

    override fun addBackground(list: MutableList<String>, it: Color, opacity: Float) {
        list.add("app:backgroundTint=\"${it.getHex(opacity)}\"")
    }
}