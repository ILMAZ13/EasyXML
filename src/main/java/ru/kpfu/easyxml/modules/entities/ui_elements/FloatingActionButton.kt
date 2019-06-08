package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document

class FloatingActionButton(document: Document) : ImageView(document) {
    companion object {
        const val KEY = "fab"
    }

    override fun getPrefix() = "fab"

    override fun isShown() = true

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<com.google.android.material.floatingactionbutton.FloatingActionButton")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")
        return list
    }
}
