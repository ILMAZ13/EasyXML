package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.figma.TypeStyle
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

open class TextView(document: Document) : View(document) {
    var text: String = ""
    var textStyle: TypeStyle? = null

    init {
        text = document.characters ?: ""
        textStyle = document.style
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<TextView")
        super.getParamLines(list, false)
        list.add("tools:text=\"$text\"")
        list.add("android:textSize=\"${textStyle?.fontSize}sp\"")
        //todo add text styles
        if (isParent)
            list.add("/>")
        return list
    }
}