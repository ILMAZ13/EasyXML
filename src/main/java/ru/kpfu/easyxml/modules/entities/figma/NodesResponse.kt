package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

data class NodesResponse(
        /**
         * utc date in iso8601
         */
        @SerializedName("lastModified")
        val lastModified: String? = null,

        val name: String? = null,

        @SerializedName("thumbnailUrl")
        val thumbnailURL: String? = null,

        val version: String? = null,

        val nodes: Map<String, DocumentWrapper>? = null
) {

    data class DocumentWrapper(
            val document: Document,
            val components: Map<String, Component>? = null
    )
}