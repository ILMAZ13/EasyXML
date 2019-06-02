package ru.kpfu.easyxml.modules.entities.figma

/**
 * A 2d vector
 *
 * This field contains three vectors, each of which are a position in
 * normalized object space (normalized object space is if the top left
 * corner of the bounding box of the object is (0, 0) and the bottom
 * right is (1,1)). The first position corresponds to the start of the
 * gradient (value 0 for the purposes of calculating gradient stops),
 * the second position is the end of the gradient (value 1), and the
 * third handle position determines the width of the gradient (only
 * relevant for non-linear gradients).
 *
 * 2d vector offset within the frame.
 */
data class Vector2(
        /**
         * X coordinate of the vector
         */
        val x: Double,

        /**
         * Y coordinate of the vector
         */
        val y: Double
)