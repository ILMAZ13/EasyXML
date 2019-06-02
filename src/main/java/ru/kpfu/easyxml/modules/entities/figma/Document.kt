package ru.kpfu.easyxml.modules.entities.figma

import com.google.gson.annotations.SerializedName

/**
 * Node Properties
 * The root node
 *
 * The root node within the document
 *
 * Represents a single page
 *
 * A node of fixed size containing other nodes
 *
 * A logical grouping of nodes
 *
 * A vector network, consisting of vertices and edges
 *
 * A group that has a boolean operation applied to it
 *
 * A regular star shape
 *
 * A straight line
 *
 * An ellipse
 *
 * A regular n-sided polygon
 *
 * A rectangle
 *
 * A text box
 *
 * A rectangular region of the canvas that can be exported
 *
 * A node that can have instances created of it that share the same properties
 * A description of a master component. Helps you identify which component
 * instances are attached to
 *
 * An instance of a component, changes to the component result in the same
 * changes applied to the instance
 */
data class Document(
        /**
         * An array of canvases attached to the document
         *
         * An array of top level layers on the canvas
         *
         * An array of nodes that are direct children of this node
         *
         * An array of nodes that are being boolean operated on
         */
        val children: List<Document>? = null,

        /**
         * a string uniquely identifying this node within the document
         */
        val id: String,

        /**
         * the name given to the node by the user in the tool.
         *
         * The name of the component
         */
        val name: String,

        /**
         * the type of the node, refer to table below for details
         */
        val type: NodeType,

        /**
         * whether or not the node is visible on the canvas
         */
        @SerializedName("visible")
        private val _visible: Boolean?,

        /**
         * Background color of the canvas
         *
         * Background color of the node
         */
        val backgroundColor: Color? = null,

        /**
         * An array of export settings representing images to export from the canvas
         *
         * An array of export settings representing images to export from node
         *
         * An array of export settings representing images to export from this node
         */
        val exportSettings: List<ExportSetting>? = null,

        /**
         * Bounding box of the node in absolute space coordinates
         */
        val absoluteBoundingBox: Rect? = null,

        /**
         * How this node blends with nodes behind it in the scene
         * (see blend mode section for more details)
         */
        val blendMode: BlendMode? = null,

        /**
         * Does this node clip content outside of its bounds?
         */
        val clipsContent: Boolean? = null,

        /**
         * Horizontal and vertical layout constraints for node
         */
        val constraints: LayoutConstraint? = null,

        /**
         * An array of effects attached to this node
         * (see effects section for more details)
         */
        val effects: List<Effect>? = null,

        /**
         * Does this node mask sibling nodes in front of it?
         */
        //todo: Setting default not work
        val isMask: Boolean = false,

        /**
         * An array of layout grids attached to this node (see layout grids section
         * for more details). GROUP nodes do not have this attribute
         */
        val layoutGrids: List<LayoutGrid>? = null,

        /**
         * Opacity of the node
         */
        @SerializedName("opacity")
        private val _opacity: Double?,

        /**
         * Keep height and width constrained to same ratio
         */
        //todo: Setting default not work
        val preserveRatio: Boolean = false,

        /**
         * Node ID of node to transition to in prototyping
         */
        val transitionNodeID: String? = null,

        /**
         * An array of fill paints applied to the node
         */
        val fills: List<Paint>? = null,

        /**
         * Where stroke is drawn relative to the vector outline as a string enum
         * "INSIDE": draw stroke inside the shape boundary
         * "OUTSIDE": draw stroke outside the shape boundary
         * "CENTER": draw stroke centered along the shape boundary
         */
        val strokeAlign: StrokeAlign? = null,

        /**
         * An array of stroke paints applied to the node
         */
        val strokes: List<Paint>? = null,

        /**
         * The weight of strokes on the node
         */
        val strokeWeight: Double? = null,

        /**
         * Radius of each corner of the rectangle
         */
        val cornerRadius: Double? = null,

        /**
         * Text contained within text box
         */
        val characters: String? = null,

        /**
         * Array with same number of elements as characeters in text box,
         * each element is a reference to the styleOverrideTable defined
         * below and maps to the corresponding character in the characters
         * field. Elements with value 0 have the default type style
         */
        val characterStyleOverrides: List<Double>? = null,

        /**
         * Style of text including font family and weight (see type style
         * section for more information)
         */
        val style: TypeStyle? = null,

        /**
         * Map from ID to TypeStyle for looking up style overrides
         */
        val styleOverrideTable: Map<Number, TypeStyle>? = null,

        /**
         * The description of the component as entered in the editor
         */
        val description: String? = null,

        /**
         * ID of component that this instance came from, refers to components
         * table (see endpoints section below)
         */
        @SerializedName(value = "componentId")
        val componentID: String? = null
) {

    companion object {
        private val images = listOf(NodeType.VECTOR, NodeType.BOOLEAN, NodeType.STAR, NodeType.LINE, NodeType.ELLIPSE, NodeType.REGULAR_POLYGON, NodeType.RECTANGLE)
    }

    val visible
        get() = _visible ?: true

    val opacity
        get() = _opacity ?: 1.0

    var isImage: Boolean = false

    override fun toString(): String = "name=$name type=$type ${super.toString()}"

    fun isImageType(): Boolean = images.contains(type)
}
