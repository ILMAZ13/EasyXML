package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * Style of text including font family and weight (see type style
 * section for more information)
 *
 * Metadata for character formatting
 *
 * Map from ID to TypeStyle for looking up style overrides
 */
data class TypeStyle(
        /**
         * Paints applied to characters
         */
        val fills: List<Paint>,

        /**
         * Font family of text (standard name)
         */
        val fontFamily: String,

        /**
         * PostScript font name
         */
        val fontPostScriptName: String,

        /**
         * Font size in px
         */
        val fontSize: Double,

        /**
         * Numeric font weight
         */
        val fontWeight: Double,

        /**
         * Is text italicized?
         */
        val italic: Boolean,

        /**
         * Space between characters in px
         */
        val letterSpacing: Double,

        /**
         * Line height as a percentage of normal line height
         */
        val lineHeightPercent: Double,

        /**
         * Line height in px
         */
        val lineHeightPx: Double,

        /**
         * Horizontal text alignment as string enum
         */
        val textAlignHorizontal: TextAlignHorizontal,

        /**
         * Vertical text alignment as string enum
         */
        val textAlignVertical: TextAlignVertical
) {

    /**
     * Vertical text alignment as string enum
     */
    enum class TextAlignVertical {
        @SerializedName("BOTTOM")
        Bottom,
        @SerializedName("CENTER")
        Center,
        @SerializedName("TOP")
        Top;
    }

    /**
     * Horizontal text alignment as string enum
     */
    enum class TextAlignHorizontal {
        @SerializedName("CENTER")
        Center,
        @SerializedName("JUSTIFIED")
        Justified,
        @SerializedName("LEFT")
        Left,
        @SerializedName("RIGHT")
        Right;
    }
}