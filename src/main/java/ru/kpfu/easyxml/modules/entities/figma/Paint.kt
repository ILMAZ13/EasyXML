package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * An array of fill paints applied to the node
 *
 * A solid color, gradient, or image texture that can be applied as fills or strokes
 *
 * An array of stroke paints applied to the node
 *
 * Paints applied to characters
 */
data class Paint(
        /**
         * Solid color of the paint
         */
        val color: Color? = null,

        /**
         * This field contains three vectors, each of which are a position in
         * normalized object space (normalized object space is if the top left
         * corner of the bounding box of the object is (0, 0) and the bottom
         * right is (1,1)). The first position corresponds to the start of the
         * gradient (value 0 for the purposes of calculating gradient stops),
         * the second position is the end of the gradient (value 1), and the
         * third handle position determines the width of the gradient (only
         * relevant for non-linear gradients).
         */
        val gradientHandlePositions: List<Vector2>? = null,

        /**
         * Positions of key points along the gradient axis with the colors
         * anchored there. Colors along the gradient are interpolated smoothly
         * between neighboring gradient stops.
         */
        val gradientStops: List<ColorStop>? = null,

        /**
         * Overall opacity of paint (colors within the paint can also have opacity
         * values which would blend with this)
         */
        val opacity: Double,

        /**
         * Image scaling mode
         */
        val scaleMode: String? = null,

        /**
         * Type of paint as a string enum
         */
        val type: FillType,

        /**
         * Is the paint enabled?
         */
        @SerializedName("visible")
        private val _visible: Boolean?
) {

    val visible: Boolean
        get() = _visible ?: true

    /**
     * Type of paint as a string enum
     */
    enum class FillType {
        @SerializedName("EMOJI")
        Emoji,
        @SerializedName("GRADIENT_ANGULAR")
        GradientAngular,
        @SerializedName("GRADIENT_DIAMOND")
        GradientDiamond,
        @SerializedName("GRADIENT_LINEAR")
        GradientLinear,
        @SerializedName("GRADIENT_RADIAL")
        GradientRadial,
        @SerializedName("IMAGE")
        Image,
        @SerializedName("SOLID")
        Solid;
    }

    /**
     * Positions of key points along the gradient axis with the colors
     * anchored there. Colors along the gradient are interpolated smoothly
     * between neighboring gradient stops.
     *
     * A position color pair representing a gradient stop
     */
    data class ColorStop(
            /**
             * Color attached to corresponding position
             */
            val color: Color,

            /**
             * Value between 0 and 1 representing position along gradient axis
             */
            val position: Double
    )
}