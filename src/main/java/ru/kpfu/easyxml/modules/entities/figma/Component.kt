package ru.kpfu.easyxml.modules.entities.figma

/**
 * A node that can have instances created of it that share the same properties
 * A description of a master component. Helps you identify which component
 * instances are attached to
 */
data class Component(
        /**
         * Bounding box of the node in absolute space coordinates
         */
        val absoluteBoundingBox: Rect,

        /**
         * Background color of the node
         */
        val backgroundColor: Color,

        /**
         * How this node blends with nodes behind it in the scene
         * (see blend mode section for more details)
         */
        val blendMode: BlendMode,

        /**
         * An array of nodes that are direct children of this node
         */
        val children: List<Document>,

        /**
         * Does this node clip content outside of its bounds?
         */
        val clipsContent: Boolean,

        /**
         * Horizontal and vertical layout constraints for node
         */
        val constraints: LayoutConstraint,

        /**
         * The description of the component as entered in the editor
         */
        val description: String,

        /**
         * An array of effects attached to this node
         * (see effects section for more details)
         */
        val effects: List<Effect>,

        /**
         * An array of export settings representing images to export from node
         */
        val exportSettings: List<ExportSetting>,

        /**
         * a string uniquely identifying this node within the document
         */
        val id: String,

        /**
         * Does this node mask sibling nodes in front of it?
         */
        val isMask: Boolean,

        /**
         * An array of layout grids attached to this node (see layout grids section
         * for more details). GROUP nodes do not have this attribute
         */
        val layoutGrids: List<LayoutGrid>,

        /**
         * The name of the component
         */
        val name: String,

        /**
         * Opacity of the node
         */
        val opacity: Double,

        /**
         * Keep height and width constrained to same ratio
         */
        val preserveRatio: Boolean,

        /**
         * Node ID of node to transition to in prototyping
         */
        val transitionNodeID: String? = null,

        /**
         * the type of the node, refer to table below for details
         */
        val type: NodeType,

        /**
         * whether or not the node is visible on the canvas
         */
        val visible: Boolean
)