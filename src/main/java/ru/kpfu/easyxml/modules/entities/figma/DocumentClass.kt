package ru.kpfu.easyxml.modules.entities.figma

/**
 * Node Properties
 * The root node
 *
 * The root node within the document
 */
data class DocumentClass(
        /**
         * An array of canvases attached to the document
         */
        val children: List<Document>,

        /**
         * a string uniquely identifying this node within the document
         */
        val id: String,

        /**
         * the name given to the node by the user in the tool.
         */
        val name: String,

        /**
         * the type of the node, refer to table below for details
         */
        val type: NodeType,

        /**
         * whether or not the node is visible on the canvas
         */
        val visible: Boolean
)