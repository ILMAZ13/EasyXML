package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup
import java.util.*

class Screen(document: Document) : ViewGroup(document) {
    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        list.add("<AbsoluteLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"")
        list.add("xmlns:app=\"http://schemas.android.com/apk/res-auto\"")
        list.add("xmlns:tools=\"http://schemas.android.com/tools\"")
        super.getParamLines(list, false)
        list.add("</AbsoluteLayout>")
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