package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * An array of export settings representing images to export from node
 *
 * Format and size to export an asset at
 *
 * An array of export settings representing images to export from this node
 *
 * An array of export settings representing images to export from the canvas
 */
data class ExportSetting(
        /**
         * Constraint that determines sizing of exported asset
         */
        val constraint: Constraint,

        /**
         * Image type, string enum
         */
        val format: Format,

        /**
         * File suffix to append to all filenames
         */
        val suffix: String
) {
    /**
     * Image type, string enum
     */
    enum class Format {
        @SerializedName("JPG")
        Jpg,
        @SerializedName("PNG")
        PNG,
        @SerializedName("SVG")
        SVG;
    }
}