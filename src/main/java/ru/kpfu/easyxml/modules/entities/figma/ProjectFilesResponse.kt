package ru.kpfu.easyxml.modules.entities.figma

/**
 * GET /v1/projects/:project_id/files
 *
 * > Description
 * List the files in a given project.
 *
 * > Path parameters
 * project_id String
 * Id of the project to list files from
 */
data class ProjectFilesResponse(
        val files: List<File>
)