package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Color
import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.figma.Paint
import ru.kpfu.easyxml.modules.entities.figma.TypeStyle
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

open class TextView(document: Document) : View(document) {
    var text: String = ""
    var textStyle: TypeStyle? = null
    var textColor: Color? = null

    init {
        text = document.characters ?: ""
        textStyle = document.style
        document.fills?.forEach {
            if (it.visible && it.type == Paint.FillType.Solid)
                it.color?.let {
                    textColor = it
                }
        }
    }

    override fun getPrefix() = "tv"

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<TextView")
        super.getParamLines(list, false)
        list.add("tools:text=\"$text\"")
        list.add("android:textSize=\"${textStyle?.fontSize}sp\"")
        textColor?.let {
            list.add("android:textColor=\"${it.getHex(document.opacity)}\"")
        }
        //todo add text styles
        if (isParent)
            list.add("/>")
        return list
    }
}