package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.figma.Document

open class ViewGroup(
        document: Document,
        var parent: ViewGroup?
) : View(document) {
    var children: MutableList<View> = ArrayList()

    override fun getPrefix() = "cl"

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (children.isNullOrEmpty())
            return list
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