package ru.kpfu.easyxml.modules.entities.figma

/**
 * GET /v1/files/:key/comments
 *
 * > Description
 * A list of comments left on the file.
 *
 * > Path parameters
 * key String
 * File to get comments from
 */
data class CommentsResponse(
        val comments: List<Comment>
)