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
        val textAlignVertical: TextAlignVertical,

        /**
         * Text Case as enum
         */
        @SerializedName("textCase")
        val _textCase: TextCase?,

        /**
         * Text decoration string as enum
         */
        @SerializedName("textDecoration")
        val _textDecoration: TextDecoration?
) {

    val textCase: TextCase
        get() = _textCase ?: TextCase.ORIGINAL

    val textDecoration: TextDecoration
        get() = _textDecoration ?: TextDecoration.NONE

    /**
     * Vertical text alignment as string enum
     */
    enum class TextAlignVertical {
        @SerializedName("BOTTOM")
        BOTTOM,
        @SerializedName("CENTER")
        CENTER,
        @SerializedName("TOP")
        TOP;
    }

    /**
     * Horizontal text alignment as string enum
     */
    enum class TextAlignHorizontal {
        @SerializedName("CENTER")
        CENTER,
        @SerializedName("JUSTIFIED")
        JUSTIFIED,
        @SerializedName("LEFT")
        LEFT,
        @SerializedName("RIGHT")
        RIGHT;
    }

    /**
     *  Text case as enum
     */
    enum class TextCase {
        @SerializedName("ORIGINAL")
        ORIGINAL,
        @SerializedName("UPPER")
        UPPER,
        @SerializedName("LOWER")
        LOWER,
        @SerializedName("TITLE")
        TITLE
    }

    /**
     * Decoration of text as enum
     */
    enum class TextDecoration {
        @SerializedName("NONE")
        NONE,
        @SerializedName("STRIKETHROUGH")
        STRIKETHROUGH,
        @SerializedName("UNDERLINE")
        UNDERLINE
    }
}