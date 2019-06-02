package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * How this node blends with nodes behind it in the scene
 * (see blend mode section for more details)
 *
 * Enum describing how layer blends with layers below
 * This type is a string enum with the following possible values
 */
enum class BlendMode {
    @SerializedName("COLOR")
    Color,
    @SerializedName("COLOR_BURN")
    ColorBurn,
    @SerializedName("COLOR_DODGE")
    ColorDodge,
    @SerializedName("DARKEN")
    Darken,
    @SerializedName("DIFFERENCE")
    Difference,
    @SerializedName("EXCLUSION")
    Exclusion,
    @SerializedName("HARD_LIGHT")
    HardLight,
    @SerializedName("HUE")
    Hue,
    @SerializedName("LIGHTEN")
    Lighten,
    @SerializedName("LINEAR_BURN")
    LinearBurn,
    @SerializedName("LINEAR_DODGE")
    LinearDodge,
    @SerializedName("LUMINOSITY")
    Luminosity,
    @SerializedName("MULTIPLY")
    Multiply,
    @SerializedName("NORMAL")
    Normal,
    @SerializedName("OVERLAY")
    Overlay,
    @SerializedName("PASS_THROUGH")
    PassThrough,
    @SerializedName("SATURATION")
    Saturation,
    @SerializedName("SCREEN")
    Screen,
    @SerializedName("SOFT_LIGHT")
    SoftLight;
}