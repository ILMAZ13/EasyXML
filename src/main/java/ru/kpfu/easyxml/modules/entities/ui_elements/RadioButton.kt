package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class RadioButton(document: Document) : View(document) {
    companion object {
        const val KEY = "radio_button"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<RadioButton")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }
}
