package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.figma.Color
import ru.kpfu.easyxml.modules.entities.figma.Document

abstract class View(var document: Document) {

    companion object {
        val MATCH_PARENT = -1.0
        val WRAP_CONTENT = -2.0
    }

    var id: String = ""
    var x = 0.0
    var y = 0.0
    var layoutHeight = 0.0
    var layoutWidth = 0.0

    var absoluteX: Double = 0.0
    var absoluteY: Double = 0.0
    var height: Double = 0.0
    var width: Double = 0.0

    var backgroundColor: Color? = null
    var radius: Double = 0.0

    var marginTop = 0.0
    var marginStart = 0.0
    var marginEnd = 0.0
    var marginBottom = 0.0

    var paddingTop = 0.0
    var paddingStart = 0.0
    var paddingEnd = 0.0
    var paddingBottom = 0.0

    var constraintTop: String? = null
    var constraintStart: String? = null
    var constraintEnd: String? = null
    var constraintBottom: String? = null

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
        when (layoutWidth) {
            WRAP_CONTENT -> list.add("android:layout_width=\"wrap_content\"")
            MATCH_PARENT -> list.add("android:layout_width=\"match_parent\"")
            0.0 -> list.add("android:layout_width=\"${width}dp\"")
            else -> list.add("android:layout_width=\"${layoutWidth}dp\"")
        }
        when (layoutHeight) {
            WRAP_CONTENT -> list.add("android:layout_height=\"wrap_content\"")
            MATCH_PARENT -> list.add("android:layout_height=\"match_parent\"")
            0.0 -> list.add("android:layout_height=\"${height}dp\"")
            else -> list.add("android:layout_height=\"${layoutHeight}dp\"")
        }

        constraintTop?.let {
            list.add("android:layout_marginTop=\"${marginTop}dp\"")
            if (it == "parent")
                list.add("app:layout_constraintTop_toTopOf=\"parent\"")
            else
                list.add("app:layout_constraintTop_toBottomOf=\"@id/$it\"")
        }
        constraintBottom?.let {
            list.add("android:layout_marginBottom=\"${marginBottom}dp\"")
            if (it == "parent")
                list.add("app:layout_constraintBottom_toBottomOf=\"parent\"")
            else
                list.add("app:layout_constraintBottom_toTopOf=\"@id/$it\"")
        }
        constraintStart?.let {
            list.add("android:layout_marginStart=\"${marginStart}dp\"")
            if (it == "parent")
                list.add("app:layout_constraintStart_toStartOf=\"parent\"")
            else
                list.add("app:layout_constraintStart_toEndOf=\"@id/$it\"")
        }
        constraintEnd?.let {
            list.add("android:layout_marginEnd=\"${marginEnd}dp\"")
            if (it == "parent")
                list.add("app:layout_constraintEnd_toEndOf=\"parent\"")
            else
                list.add("app:layout_constraintEnd_toStartOf=\"@id/$it\"")
        }
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