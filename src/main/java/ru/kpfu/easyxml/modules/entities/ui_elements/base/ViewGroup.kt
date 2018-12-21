package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.Document

open class ViewGroup(document: Document) : View(document) {
    var children: MutableList<View> = ArrayList()

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<AbsoluteLayout")
        super.getParamLines(list, false)
        list.add(">")
        children.forEach {
            it.getParamLines(list, true)
            list.add("")
        }
        if (isParent)
            list.add("</AbsoluteLayout>")

        return list
    }
}