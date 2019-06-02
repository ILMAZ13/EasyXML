package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * A comment or reply left by a user
 */
data class Comment(
        @SerializedName(value = "client_meta")
        val clientMeta: ClientMeta,

        /**
         * The time at which the comment was left
         */
        @SerializedName(value = "created_at")
        val createdAt: String,

        /**
         * The file in which the comment lives
         */
        @SerializedName(value = "file_key")
        val fileKey: String,

        /**
         * Unique identifier for comment
         */
        val id: String,

        /**
         * (MISSING IN DOCS)
         * The content of the comment
         */
        val message: String,

        /**
         * Only set for top level comments. The number displayed with the
         * comment in the UI
         */
        @SerializedName(value = "order_id")
        val orderID: Double,

        /**
         * If present, the id of the comment to which this is the reply
         */
        @SerializedName(value = "parent_id")
        val parentID: String,

        /**
         * If set, when the comment was resolved
         */
        @SerializedName(value = "resolved_at")
        val resolvedAt: String? = null,

        /**
         * The user who left the comment
         */
        val user: User
)