package ru.kpfu.easyxml.modules.entities.figma

/**
 * Background color of the node
 *
 * An RGBA color
 *
 * Background color of the canvas
 *
 * Solid color of the paint
 *
 * Color attached to corresponding position
 *
 * Color of the grid
 */
data class Color(
        /**
         * Alpha channel value, between 0 and 1
         */
        val a: Float,

        /**
         * Blue channel value? = null, between 0 and 1
         */
        val b: Float,

        /**
         * Green channel value, between 0 and 1
         */
        val g: Float,

        /**
         * Red channel value, between 0 and 1
         */
        val r: Float
) {
    companion object {
        fun getHex(color: Color): String = "#${Integer.toHexString(java.awt.Color(color.r, color.g, color.b, color.a).rgb)}"
    }
}