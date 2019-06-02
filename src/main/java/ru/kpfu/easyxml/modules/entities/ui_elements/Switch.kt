package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Color
import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

class Switch(document: Document) : View(document) {
    companion object {
        const val KEY = "switch"
    }

    var thumbTint: Color? = null
    var trackTint: Color? = null

    override fun getPrefix() = "sw"

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<androidx.appcompat.widget.SwitchCompat")
        super.getParamLines(list, false)
        thumbTint?.let {
            list.add("app:thumbTint=\"${it.getHex(document.opacity)}\"")
        }
        trackTint?.let {
            list.add("app:trackTint=\"${it.getHex(document.opacity)}\"")
        }
        if (isParent)
            list.add("/>")

        return list
    }
}
