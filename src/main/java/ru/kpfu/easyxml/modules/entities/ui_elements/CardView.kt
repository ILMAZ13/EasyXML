package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class CardView(document: Document,
               parent: ViewGroup?
) : ViewGroup(document, parent) {

    override fun getPrefix() = "cv"

    override fun isShown() = true

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<androidx.cardview.widget.CardView")
        super.getParamLines(list, false)
        if (isParent)
            list.add("</androidx.cardview.widget.CardView>")

        return list
    }
}