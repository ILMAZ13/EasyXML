package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * Horizontal and vertical layout constraints for node
 *
 * Layout constraint relative to containing Frame
 */
data class LayoutConstraint(
        /**
         * Horizontal constraint as an enum
         * "LEFT": Node is laid out relative to left of the containing frame
         * "RIGHT": Node is laid out relative to right of the containing frame
         * "CENTER": Node is horizontally centered relative to containing frame
         * "LEFT_RIGHT": Both left and right of node are constrained relative to containing frame
         * (node stretches with frame)
         * "SCALE": Node scales horizontally with containing frame
         */
        val horizontal: Horizontal,

        /**
         * Vertical constraint as an enum
         * "TOP": Node is laid out relative to top of the containing frame
         * "BOTTOM": Node is laid out relative to bottom of the containing frame
         * "CENTER": Node is vertically centered relative to containing frame
         * "TOP_BOTTOM": Both top and bottom of node are constrained relative to containing frame
         * (node stretches with frame)
         * "SCALE": Node scales vertically with containing frame
         */
        val vertical: Vertical
) {

    /**
     * Horizontal constraint as an enum
     * "LEFT": Node is laid out relative to left of the containing frame
     * "RIGHT": Node is laid out relative to right of the containing frame
     * "CENTER": Node is horizontally centered relative to containing frame
     * "LEFT_RIGHT": Both left and right of node are constrained relative to containing frame
     * (node stretches with frame)
     * "SCALE": Node scales horizontally with containing frame
     */
    enum class Horizontal {
        @SerializedName("MIXED")
        Mixed,
        @SerializedName("CENTER")
        Center,
        @SerializedName("LEFT")
        Left,
        @SerializedName("LEFT_RIGHT")
        LeftRight,
        @SerializedName("RIGHT")
        Right,
        @SerializedName("SCALE")
        Scale;
    }

    /**
     * Vertical constraint as an enum
     * "TOP": Node is laid out relative to top of the containing frame
     * "BOTTOM": Node is laid out relative to bottom of the containing frame
     * "CENTER": Node is vertically centered relative to containing frame
     * "TOP_BOTTOM": Both top and bottom of node are constrained relative to containing frame
     * (node stretches with frame)
     * "SCALE": Node scales vertically with containing frame
     */
    enum class Vertical {
        @SerializedName("MIXED")
        Mixed,
        @SerializedName("BOTTOM")
        Bottom,
        @SerializedName("CENTER")
        Center,
        @SerializedName("SCALE")
        Scale,
        @SerializedName("TOP")
        Top,
        @SerializedName("TOP_BOTTOM")
        TopBottom;
    }
}