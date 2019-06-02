package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * the type of the node, refer to table below for details
 */
enum class NodeType {
    @SerializedName("CANVAS")
    CANVAS,
    @SerializedName("COMPONENT")
    COMPONENT,
    @SerializedName("DOCUMENT")
    DOCUMENT,
    @SerializedName("ELLIPSE")
    ELLIPSE,
    @SerializedName("FRAME")
    FRAME,
    @SerializedName("GROUP")
    GROUP,
    @SerializedName("INSTANCE")
    INSTANCE,
    @SerializedName("LINE")
    LINE,
    @SerializedName("BOOLEAN")
    BOOLEAN,
    @SerializedName("RECTANGLE")
    RECTANGLE,
    @SerializedName("REGULAR_POLYGON")
    REGULAR_POLYGON,
    @SerializedName("SLICE")
    SLICE,
    @SerializedName("STAR")
    STAR,
    @SerializedName("TEXT")
    TEXT,
    @SerializedName("VECTOR")
    VECTOR;
}