package ru.kpfu.easyxml.modules.entities.figma

/**
 * GET /v1/teams/:team_id/projects
 *
 * > Description
 * Lists the projects for a specified team. Note that this will only return projects visible
 * to the authenticated user or owner of the developer token.
 *
 * > Path parameters
 * team_id String
 * Id of the team to list projects from
 */
data class ProjectsResponse(
        val projects: List<Project>
)