package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class CardView(document: Document) : ViewGroup(document) {

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<android.support.v7.widget.CardView")
        super.getParamLines(list, false)
        if (isParent)
            list.add("</android.support.v7.widget.CardView>")

        return list
    }
}