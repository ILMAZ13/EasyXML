package ru.kpfu.easyxml.modules.entities.properties

import java.awt.Color

data class Color(var r: Float,
            var g: Float,
            var b: Float,
            var a: Float) {

    override fun toString(): String {
        return "#${Integer.toHexString(Color(r, g, b, a).rgb)}"
    }
}