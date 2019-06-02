package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * An array of effects attached to this node
 * (see effects section for more details)
 *
 * A visual effect such as a shadow or blur
 */
data class Effect(
        /**
         * Enum describing how layer blends with layers below
         * This type is a string enum with the following possible values
         */
        val blendMode: BlendMode? = null,

        /**
         * An RGBA color
         */
        val color: Color? = null,

        /**
         * A 2d vector
         */
        val offset: Vector2? = null,

        /**
         * Radius of the blur effect (applies to shadows as well)
         */
        val radius: Double,

        /**
         * Type of effect as a string enum
         */
        val type: EffectType,

        /**
         * Is the effect active?
         */
        val visible: Boolean
) {

    /**
     * Type of effect as a string enum
     */
    enum class EffectType {
        @SerializedName("BACKGROUND_BLUR")
        BackgroundBlur,
        @SerializedName("DROP_SHADOW")
        DropShadow,
        @SerializedName("INNER_SHADOW")
        InnerShadow,
        @SerializedName("LAYER_BLUR")
        LayerBlur;
    }
}