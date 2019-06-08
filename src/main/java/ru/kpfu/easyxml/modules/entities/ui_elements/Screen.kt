package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup
import java.util.*

class Screen(document: Document) : ViewGroup(document, null) {

    override fun getPrefix() = "cl"

    override fun isShown() = true

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        list.add("<androidx.constraintlayout.widget.ConstraintLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"")
        list.add("xmlns:app=\"http://schemas.android.com/apk/res-auto\"")
        list.add("xmlns:tools=\"http://schemas.android.com/tools\"")
        super.getParamLines(list, false)
        list.add("</androidx.constraintlayout.widget.ConstraintLayout>")
        return list
    }

    fun getString(): String {
        val lines = getParamLines(LinkedList(), true)

        val builder = StringBuilder()
        lines.forEach {
            builder.append(it)
            builder.append("\n")
        }

        return builder.toString()
    }
}