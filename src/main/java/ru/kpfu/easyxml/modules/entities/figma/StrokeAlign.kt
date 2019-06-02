package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * Where stroke is drawn relative to the vector outline as a string enum
 * "INSIDE": draw stroke inside the shape boundary
 * "OUTSIDE": draw stroke outside the shape boundary
 * "CENTER": draw stroke centered along the shape boundary
 */
enum class StrokeAlign {
    @SerializedName("CENTER")
    Center,
    @SerializedName("INSIDE")
    Inside,
    @SerializedName("OUTSIDE")
    Outside;
}