package ru.kpfu.easyxml.modules.entities.figma

/**
 * GET /v1/files/:key
 *
 * > Description
 *
 * Returns the document refered to by :key as a JSON object. The file key can be parsed from
 * any Figma file url: https://www.figma.com/file/:key/:title. The "document" attribute
 * contains a Node of type DOCUMENT.
 *
 * The "components" key contains a mapping from node IDs to component metadata. This is to
 * help you determine which components each instance comes from. Currently the only piece of
 * metadata available on components is the name of the component, but more properties will
 * be forthcoming.
 *
 * > Path parameters
 *
 * key String
 * File to export JSON from
 */
data class FileResponse(
        /**
         * A mapping from node IDs to component metadata. This is to help you determine which
         * components each instance comes from. Currently the only piece of metadata available on
         * components is the name of the component, but more properties will be forthcoming.
         */
        val components: Map<String, Component>,

        /**
         * The root node within the document
         */
        val document: DocumentClass,

        val schemaVersion: Double
)