package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Color
import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class RadioButton(document: Document) : View(document) {
    companion object {
        const val KEY = "radio_button"
    }

    override fun getPrefix() = "rb"

    override fun isShown() = true

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<androidx.appcompat.widget.AppCompatRadioButton")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }

    override fun addBackground(list: MutableList<String>, it: Color, opacity: Float) {
        list.add("app:buttonTint=\"${it.getHex(opacity)}\"")
    }
}
