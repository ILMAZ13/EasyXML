package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * Constraint that determines sizing of exported asset
 *
 * Sizing constraint for exports
 */
data class Constraint(
        /**
         * Type of constraint to apply; string enum with potential values below
         * "SCALE": Scale by value
         * "WIDTH": Scale proportionally and set width to value
         * "HEIGHT": Scale proportionally and set height to value
         */
        val type: ConstraintType,

        /**
         * See type property for effect of this field
         */
        val value: Double
) {

    /**
     * Type of constraint to apply; string enum with potential values below
     * "SCALE": Scale by value
     * "WIDTH": Scale proportionally and set width to value
     * "HEIGHT": Scale proportionally and set height to value
     */
    enum class ConstraintType {
        @SerializedName("HEIGHT")
        Height,
        @SerializedName("SCALE")
        Scale,
        @SerializedName("WIDTH")
        Width;
    }
}