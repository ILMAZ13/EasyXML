package ru.kpfu.easyxml.modules.entities.figma

/**
 * Bounding box of the node in absolute space coordinates
 *
 * A rectangle that expresses a bounding box in absolute coordinates
 */
data class Rect(
        /**
         * Height of the rectangle
         */
        val height: Double,

        /**
         * Width of the rectangle
         */
        val width: Double,

        /**
         * X coordinate of top left corner of the rectangle
         */
        val x: Double,

        /**
         * Y coordinate of top left corner of the rectangle
         */
        val y: Double
)