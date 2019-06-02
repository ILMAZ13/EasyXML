package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document

class FloatingActionButton(document: Document) : Icon(document) {
    companion object {
        const val KEY = "fab"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<android.support.design.widget.FloatingActionButton")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")
        return list
    }
}
