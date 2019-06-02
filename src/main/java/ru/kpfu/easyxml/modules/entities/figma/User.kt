package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * The user who left the comment
 *
 * A description of a user
 */
data class User(
        val handle: String,

        @SerializedName(value = "img_url")
        val imgURL: String
)