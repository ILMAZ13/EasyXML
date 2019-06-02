package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.figma.Color
import ru.kpfu.easyxml.modules.entities.figma.Document

abstract class View(var document: Document) {
    var id: String = ""
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
        id = uniName(document.name, getPrefix())
        document.absoluteBoundingBox?.let {
            absoluteX = it.x
            absoluteY = it.y
            height = it.height
            width = it.width
        }
    }

    open fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        list.add("android:id=\"@+id/$id\"")
        list.add("android:layout_width=\"${width}dp\"")
        list.add("android:layout_height=\"${height}dp\"")
        list.add("android:layout_x=\"${x}dp\"")
        list.add("android:layout_y=\"${y}dp\"")
        backgroundColor?.let {
            addBackground(list, it, document.opacity)
        }
        return list
    }

    open fun addBackground(list: MutableList<String>, it: Color, opacity: Float = 1F) {
        list.add("android:background=\"${it.getHex(opacity)}\"")
    }

    fun uniName(name: String, prefix: String) =
            prefix + "_" + name.toLowerCase().replace(' ', '_', true)

    abstract fun getPrefix(): String
}