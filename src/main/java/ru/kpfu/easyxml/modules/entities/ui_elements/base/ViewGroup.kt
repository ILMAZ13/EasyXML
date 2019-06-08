package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.figma.Document

open class ViewGroup(
        document: Document,
        var parent: ViewGroup?
) : View(document) {
    var children: MutableList<View> = ArrayList()

    override fun getPrefix() = "cl"

    override fun isShown() = true

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (children.isNullOrEmpty())
            return list
        if (isParent)
            list.add("<androidx.constraintlayout.widget.ConstraintLayout")
        super.getParamLines(list, false)
        list.add(">")
        children.forEach {
            it.getParamLines(list, true)
            list.add("")
        }
        if (isParent)
            list.add("</androidx.constraintlayout.widget.ConstraintLayout>")

        return list
    }

    fun getParamLinesWithChildConstraint(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (children.isNullOrEmpty())
            return list
        if (isParent)
            list.add("<androidx.constraintlayout.widget.ConstraintLayout")
        super.getParamLines(list, false)
        list.add(">")
        list.add("<androidx.constraintlayout.widget.ConstraintLayout")
        list.add("android:layout_width=\"match_parent\"")
        list.add("android:layout_height=\"match_parent\"")
        list.add(">")
        children.forEach {
            it.getParamLines(list, true)
            list.add("")
        }
        if (isParent)
            list.add("</androidx.constraintlayout.widget.ConstraintLayout>")
        list.add("</androidx.constraintlayout.widget.ConstraintLayout>")
        return list
    }
}