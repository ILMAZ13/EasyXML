package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

data class File(
        val key: String,

        /**
         * utc date in iso8601
         */
        @SerializedName(value = "last_modified")
        val lastModified: String,

        val name: String,

        @SerializedName(value = "thumbnail_url")
        val thumbnailURL: String
)