package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.Color
import ru.kpfu.easyxml.modules.entities.Document

open class View(var document: Document) {
    var name: String = ""
    var x = 0.0
    var y = 0.0
    var absoluteX: Double = 0.0
    var absoluteY: Double = 0.0
    var height: Double = 0.0
    var width: Double = 0.0
    var backgroundColor: Color? = null
    var radius: Double = 0.0

    init {
//        backgroundColor = document.backgroundColor
        name = document.name ?: ""
        document.absoluteBoundingBox?.let {
            absoluteX = it.x ?: 0.0
            absoluteY = it.y ?: 0.0
            height = it.height ?: 0.0
            width = it.width ?: 0.0
        }
    }

    open fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        list.add("android:layout_width=\"${width}dp\"")
        list.add("android:layout_height=\"${height}dp\"")
        list.add("android:layout_x=\"${x}dp\"")
        list.add("android:layout_y=\"${y}dp\"")
        backgroundColor?.let {
            list.add("android:background=\"${Color.getHex(it)}\"")
        }
        return list
    }
}