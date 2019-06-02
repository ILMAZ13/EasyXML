package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View

open class ImageView(document: Document) : View(document) {
    val iconSrc = uniName(document.name, "ic")

    override fun getPrefix() = "iv"

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<ImageView")
        super.getParamLines(list, false)
        list.add("android:src=\"@drawable/$iconSrc\"")
        if (isParent)
            list.add("/>")

        return list
    }
}