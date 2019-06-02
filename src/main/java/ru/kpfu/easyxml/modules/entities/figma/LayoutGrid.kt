package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * An array of layout grids attached to this node (see layout grids section
 * for more details). GROUP nodes do not have this attribute
 *
 * Guides to align and place objects within a frame
 */
data class LayoutGrid(
        /**
         * Positioning of grid as a string enum
         * "MIN": Grid starts at the left or top of the frame
         * "MAX": Grid starts at the right or bottom of the frame
         * "CENTER": Grid is center aligned
         */
        val alignment: Alignment,

        /**
         * Color of the grid
         */
        val color: Color,

        /**
         * Number of columns or rows
         */
        val count: Double,

        /**
         * Spacing in between columns and rows
         */
        val gutterSize: Double,

        /**
         * Spacing before the first column or row
         */
        val offset: Double,

        /**
         * Orientation of the grid as a string enum
         * "COLUMNS": Vertical grid
         * "ROWS": Horizontal grid
         * "GRID": Square grid
         */
        val pattern: Pattern,

        /**
         * Width of column grid or height of row grid or square grid spacing
         */
        val sectionSize: Double,

        /**
         * Is the grid currently visible?
         */
        val visible: Boolean
) {
    /**
     * Orientation of the grid as a string enum
     * "COLUMNS": Vertical grid
     * "ROWS": Horizontal grid
     * "GRID": Square grid
     */
    enum class Pattern {
        @SerializedName("COLUMNS")
        Columns,
        @SerializedName("GRID")
        Grid,
        @SerializedName("ROWS")
        Rows;
    }

    /**
     * Positioning of grid as a string enum
     * "MIN": Grid starts at the left or top of the frame
     * "MAX": Grid starts at the right or bottom of the frame
     * "CENTER": Grid is center aligned
     */
    enum class Alignment {
        @SerializedName("CENTER")
        Center,
        @SerializedName("MAX")
        Max,
        @SerializedName("MIN")
        Min;
    }
}