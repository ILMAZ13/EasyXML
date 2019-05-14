package ru.kpfu.easyxml.modules.entities

import com.google.gson.annotations.SerializedName


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

/**
 * Bounding box of the node in absolute space coordinates
 *
 * A rectangle that expresses a bounding box in absolute coordinates
 */
data class Rect(
        /**
         * Height of the rectangle
         */
        val height: Double,

        /**
         * Width of the rectangle
         */
        val width: Double,

        /**
         * X coordinate of top left corner of the rectangle
         */
        val x: Double,

        /**
         * Y coordinate of top left corner of the rectangle
         */
        val y: Double
)

/**
 * Background color of the node
 *
 * An RGBA color
 *
 * Background color of the canvas
 *
 * Solid color of the paint
 *
 * Color attached to corresponding position
 *
 * Color of the grid
 */
data class Color(
        /**
         * Alpha channel value, between 0 and 1
         */
        val a: Float,

        /**
         * Blue channel value? = null, between 0 and 1
         */
        val b: Float,

        /**
         * Green channel value, between 0 and 1
         */
        val g: Float,

        /**
         * Red channel value, between 0 and 1
         */
        val r: Float
) {
    companion object {
        fun getHex(color: Color): String = "#${Integer.toHexString(java.awt.Color(color.r, color.g, color.b, color.a).rgb)}"
    }
}

/**
 * How this node blends with nodes behind it in the scene
 * (see blend mode section for more details)
 *
 * Enum describing how layer blends with layers below
 * This type is a string enum with the following possible values
 */
enum class BlendMode {
    @SerializedName("COLOR")
    Color,
    @SerializedName("COLOR_BURN")
    ColorBurn,
    @SerializedName("COLOR_DODGE")
    ColorDodge,
    @SerializedName("DARKEN")
    Darken,
    @SerializedName("DIFFERENCE")
    Difference,
    @SerializedName("EXCLUSION")
    Exclusion,
    @SerializedName("HARD_LIGHT")
    HardLight,
    @SerializedName("HUE")
    Hue,
    @SerializedName("LIGHTEN")
    Lighten,
    @SerializedName("LINEAR_BURN")
    LinearBurn,
    @SerializedName("LINEAR_DODGE")
    LinearDodge,
    @SerializedName("LUMINOSITY")
    Luminosity,
    @SerializedName("MULTIPLY")
    Multiply,
    @SerializedName("NORMAL")
    Normal,
    @SerializedName("OVERLAY")
    Overlay,
    @SerializedName("PASS_THROUGH")
    PassThrough,
    @SerializedName("SATURATION")
    Saturation,
    @SerializedName("SCREEN")
    Screen,
    @SerializedName("SOFT_LIGHT")
    SoftLight;
}

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
        val visible: Boolean,

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
        val isMask: Boolean? = null,

        /**
         * An array of layout grids attached to this node (see layout grids section
         * for more details). GROUP nodes do not have this attribute
         */
        val layoutGrids: List<LayoutGrid>? = null,

        /**
         * Opacity of the node
         */
        val opacity: Double? = null,

        /**
         * Keep height and width constrained to same ratio
         */
        val preserveRatio: Boolean? = null,

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
    override fun toString(): String = "name=$name type=$type ${super.toString()}"
}

/**
 * Horizontal and vertical layout constraints for node
 *
 * Layout constraint relative to containing Frame
 */
data class LayoutConstraint(
        /**
         * Horizontal constraint as an enum
         * "LEFT": Node is laid out relative to left of the containing frame
         * "RIGHT": Node is laid out relative to right of the containing frame
         * "CENTER": Node is horizontally centered relative to containing frame
         * "LEFT_RIGHT": Both left and right of node are constrained relative to containing frame
         * (node stretches with frame)
         * "SCALE": Node scales horizontally with containing frame
         */
        val horizontal: Horizontal,

        /**
         * Vertical constraint as an enum
         * "TOP": Node is laid out relative to top of the containing frame
         * "BOTTOM": Node is laid out relative to bottom of the containing frame
         * "CENTER": Node is vertically centered relative to containing frame
         * "TOP_BOTTOM": Both top and bottom of node are constrained relative to containing frame
         * (node stretches with frame)
         * "SCALE": Node scales vertically with containing frame
         */
        val vertical: Vertical
)

/**
 * Horizontal constraint as an enum
 * "LEFT": Node is laid out relative to left of the containing frame
 * "RIGHT": Node is laid out relative to right of the containing frame
 * "CENTER": Node is horizontally centered relative to containing frame
 * "LEFT_RIGHT": Both left and right of node are constrained relative to containing frame
 * (node stretches with frame)
 * "SCALE": Node scales horizontally with containing frame
 */
enum class Horizontal {
    @SerializedName("CENTER")
    Center,
    @SerializedName("LEFT")
    Left,
    @SerializedName("LEFT_RIGHT")
    LeftRight,
    @SerializedName("RIGHT")
    Right,
    @SerializedName("SCALE")
    Scale;
}

/**
 * Vertical constraint as an enum
 * "TOP": Node is laid out relative to top of the containing frame
 * "BOTTOM": Node is laid out relative to bottom of the containing frame
 * "CENTER": Node is vertically centered relative to containing frame
 * "TOP_BOTTOM": Both top and bottom of node are constrained relative to containing frame
 * (node stretches with frame)
 * "SCALE": Node scales vertically with containing frame
 */
enum class Vertical {
    @SerializedName("BOTTOM")
    Bottom,
    @SerializedName("CENTER")
    Center,
    @SerializedName("SCALE")
    Scale,
    @SerializedName("TOP")
    Top,
    @SerializedName("TOP_BOTTOM")
    TopBottom;
}

/**
 * An array of effects attached to this node
 * (see effects section for more details)
 *
 * A visual effect such as a shadow or blur
 */
data class Effect(
        /**
         * Enum describing how layer blends with layers below
         * This type is a string enum with the following possible values
         */
        val blendMode: BlendMode? = null,

        /**
         * An RGBA color
         */
        val color: Color? = null,

        /**
         * A 2d vector
         */
        val offset: Vector2? = null,

        /**
         * Radius of the blur effect (applies to shadows as well)
         */
        val radius: Double,

        /**
         * Type of effect as a string enum
         */
        val type: EffectType,

        /**
         * Is the effect active?
         */
        val visible: Boolean
)

/**
 * A 2d vector
 *
 * This field contains three vectors, each of which are a position in
 * normalized object space (normalized object space is if the top left
 * corner of the bounding box of the object is (0, 0) and the bottom
 * right is (1,1)). The first position corresponds to the start of the
 * gradient (value 0 for the purposes of calculating gradient stops),
 * the second position is the end of the gradient (value 1), and the
 * third handle position determines the width of the gradient (only
 * relevant for non-linear gradients).
 *
 * 2d vector offset within the frame.
 */
data class Vector2(
        /**
         * X coordinate of the vector
         */
        val x: Double,

        /**
         * Y coordinate of the vector
         */
        val y: Double
)

/**
 * Type of effect as a string enum
 */
enum class EffectType {
    @SerializedName("BACKGROUND_BLUR")
    BackgroundBlur,
    @SerializedName("DROP_SHADOW")
    DropShadow,
    @SerializedName("INNER_SHADOW")
    InnerShadow,
    @SerializedName("LAYER_BLUR")
    LayerBlur;
}

/**
 * An array of export settings representing images to export from node
 *
 * Format and size to export an asset at
 *
 * An array of export settings representing images to export from this node
 *
 * An array of export settings representing images to export from the canvas
 */
data class ExportSetting(
        /**
         * Constraint that determines sizing of exported asset
         */
        val constraint: Constraint,

        /**
         * Image type, string enum
         */
        val format: Format,

        /**
         * File suffix to append to all filenames
         */
        val suffix: String
)

/**
 * Constraint that determines sizing of exported asset
 *
 * Sizing constraint for exports
 */
data class Constraint(
        /**
         * Type of constraint to apply; string enum with potential values below
         * "SCALE": Scale by value
         * "WIDTH": Scale proportionally and set width to value
         * "HEIGHT": Scale proportionally and set height to value
         */
        val type: ConstraintType,

        /**
         * See type property for effect of this field
         */
        val value: Double
)

/**
 * Type of constraint to apply; string enum with potential values below
 * "SCALE": Scale by value
 * "WIDTH": Scale proportionally and set width to value
 * "HEIGHT": Scale proportionally and set height to value
 */
enum class ConstraintType {
    @SerializedName("HEIGHT")
    Height,
    @SerializedName("SCALE")
    Scale,
    @SerializedName("WIDTH")
    Width;
}

/**
 * Image type, string enum
 */
enum class Format {
    @SerializedName("JPG")
    Jpg,
    @SerializedName("PNG")
    PNG,
    @SerializedName("SVG")
    SVG;
}

/**
 * An array of fill paints applied to the node
 *
 * A solid color, gradient, or image texture that can be applied as fills or strokes
 *
 * An array of stroke paints applied to the node
 *
 * Paints applied to characters
 */
data class Paint(
        /**
         * Solid color of the paint
         */
        val color: Color? = null,

        /**
         * This field contains three vectors, each of which are a position in
         * normalized object space (normalized object space is if the top left
         * corner of the bounding box of the object is (0, 0) and the bottom
         * right is (1,1)). The first position corresponds to the start of the
         * gradient (value 0 for the purposes of calculating gradient stops),
         * the second position is the end of the gradient (value 1), and the
         * third handle position determines the width of the gradient (only
         * relevant for non-linear gradients).
         */
        val gradientHandlePositions: List<Vector2>? = null,

        /**
         * Positions of key points along the gradient axis with the colors
         * anchored there. Colors along the gradient are interpolated smoothly
         * between neighboring gradient stops.
         */
        val gradientStops: List<ColorStop>? = null,

        /**
         * Overall opacity of paint (colors within the paint can also have opacity
         * values which would blend with this)
         */
        val opacity: Double,

        /**
         * Image scaling mode
         */
        val scaleMode: String? = null,

        /**
         * Type of paint as a string enum
         */
        val type: FillType,

        /**
         * Is the paint enabled?
         */
        val visible: Boolean
)

/**
 * Positions of key points along the gradient axis with the colors
 * anchored there. Colors along the gradient are interpolated smoothly
 * between neighboring gradient stops.
 *
 * A position color pair representing a gradient stop
 */
data class ColorStop(
        /**
         * Color attached to corresponding position
         */
        val color: Color,

        /**
         * Value between 0 and 1 representing position along gradient axis
         */
        val position: Double
)

/**
 * Type of paint as a string enum
 */
enum class FillType {
    @SerializedName("EMOJI")
    Emoji,
    @SerializedName("GRADIENT_ANGULAR")
    GradientAngular,
    @SerializedName("GRADIENT_DIAMOND")
    GradientDiamond,
    @SerializedName("GRADIENT_LINEAR")
    GradientLinear,
    @SerializedName("GRADIENT_RADIAL")
    GradientRadial,
    @SerializedName("IMAGE")
    Image,
    @SerializedName("SOLID")
    Solid;
}

/**
 * An array of layout grids attached to this node (see layout grids section
 * for more details). GROUP nodes do not have this attribute
 *
 * Guides to align and place objects within a frame
 */
data class LayoutGrid(
        /**
         * Positioning of grid as a string enum
         * "MIN": Grid starts at the left or top of the frame
         * "MAX": Grid starts at the right or bottom of the frame
         * "CENTER": Grid is center aligned
         */
        val alignment: Alignment,

        /**
         * Color of the grid
         */
        val color: Color,

        /**
         * Number of columns or rows
         */
        val count: Double,

        /**
         * Spacing in between columns and rows
         */
        val gutterSize: Double,

        /**
         * Spacing before the first column or row
         */
        val offset: Double,

        /**
         * Orientation of the grid as a string enum
         * "COLUMNS": Vertical grid
         * "ROWS": Horizontal grid
         * "GRID": Square grid
         */
        val pattern: Pattern,

        /**
         * Width of column grid or height of row grid or square grid spacing
         */
        val sectionSize: Double,

        /**
         * Is the grid currently visible?
         */
        val visible: Boolean
)

/**
 * Positioning of grid as a string enum
 * "MIN": Grid starts at the left or top of the frame
 * "MAX": Grid starts at the right or bottom of the frame
 * "CENTER": Grid is center aligned
 */
enum class Alignment {
    @SerializedName("CENTER")
    Center,
    @SerializedName("MAX")
    Max,
    @SerializedName("MIN")
    Min;
}

/**
 * Orientation of the grid as a string enum
 * "COLUMNS": Vertical grid
 * "ROWS": Horizontal grid
 * "GRID": Square grid
 */
enum class Pattern {
    @SerializedName("COLUMNS")
    Columns,
    @SerializedName("GRID")
    Grid,
    @SerializedName("ROWS")
    Rows;
}

/**
 * Where stroke is drawn relative to the vector outline as a string enum
 * "INSIDE": draw stroke inside the shape boundary
 * "OUTSIDE": draw stroke outside the shape boundary
 * "CENTER": draw stroke centered along the shape boundary
 */
enum class StrokeAlign {
    @SerializedName("CENTER")
    Center,
    @SerializedName("INSIDE")
    Inside,
    @SerializedName("OUTSIDE")
    Outside;
}

/**
 * Style of text including font family and weight (see type style
 * section for more information)
 *
 * Metadata for character formatting
 *
 * Map from ID to TypeStyle for looking up style overrides
 */
data class TypeStyle(
        /**
         * Paints applied to characters
         */
        val fills: List<Paint>,

        /**
         * Font family of text (standard name)
         */
        val fontFamily: String,

        /**
         * PostScript font name
         */
        val fontPostScriptName: String,

        /**
         * Font size in px
         */
        val fontSize: Double,

        /**
         * Numeric font weight
         */
        val fontWeight: Double,

        /**
         * Is text italicized?
         */
        val italic: Boolean,

        /**
         * Space between characters in px
         */
        val letterSpacing: Double,

        /**
         * Line height as a percentage of normal line height
         */
        val lineHeightPercent: Double,

        /**
         * Line height in px
         */
        val lineHeightPx: Double,

        /**
         * Horizontal text alignment as string enum
         */
        val textAlignHorizontal: TextAlignHorizontal,

        /**
         * Vertical text alignment as string enum
         */
        val textAlignVertical: TextAlignVertical
)

/**
 * Horizontal text alignment as string enum
 */
enum class TextAlignHorizontal {
    @SerializedName("CENTER")
    Center,
    @SerializedName("JUSTIFIED")
    Justified,
    @SerializedName("LEFT")
    Left,
    @SerializedName("RIGHT")
    Right;
}

/**
 * Vertical text alignment as string enum
 */
enum class TextAlignVertical {
    @SerializedName("BOTTOM")
    Bottom,
    @SerializedName("CENTER")
    Center,
    @SerializedName("TOP")
    Top;
}

/**
 * the type of the node, refer to table below for details
 */
enum class NodeType {
    @SerializedName("CANVAS")
    CANVAS,
    @SerializedName("COMPONENT")
    COMPONENT,
    @SerializedName("DOCUMENT")
    DOCUMENT,
    @SerializedName("ELLIPSE")
    ELLIPSE,
    @SerializedName("FRAME")
    FRAME,
    @SerializedName("GROUP")
    GROUP,
    @SerializedName("INSTANCE")
    INSTANCE,
    @SerializedName("LINE")
    LINE,
    @SerializedName("BOOLEAN")
    NODETYPEBOOLEAN,
    @SerializedName("RECTANGLE")
    RECTANGLE,
    @SerializedName("REGULAR_POLYGON")
    REGULARPOLYGON,
    @SerializedName("SLICE")
    SLICE,
    @SerializedName("STAR")
    STAR,
    @SerializedName("TEXT")
    TEXT,
    @SerializedName("VECTOR")
    VECTOR;
}

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

/**
 * A 2d vector
 *
 * This field contains three vectors, each of which are a position in
 * normalized object space (normalized object space is if the top left
 * corner of the bounding box of the object is (0, 0) and the bottom
 * right is (1,1)). The first position corresponds to the start of the
 * gradient (value 0 for the purposes of calculating gradient stops),
 * the second position is the end of the gradient (value 1), and the
 * third handle position determines the width of the gradient (only
 * relevant for non-linear gradients).
 *
 * 2d vector offset within the frame.
 *
 * A relative offset within a frame
 */
data class ClientMeta(
        /**
         * X coordinate of the vector
         */
        val x: Double? = null,

        /**
         * Y coordinate of the vector
         */
        val y: Double? = null,

        /**
         * Unique id specifying the frame.
         */
        @SerializedName(value = "node_id")
        val nodeID: List<String>? = null,

        /**
         * 2d vector offset within the frame.
         */
        @SerializedName(value = "node_offset")
        val nodeOffset: Vector2? = null
)

/**
 * The user who left the comment
 *
 * A description of a user
 */
data class User(
        val handle: String,

        @SerializedName(value = "img_url")
        val imgURL: String
)

/**
 * POST /v1/files/:key/comments
 *
 * > Description
 * Posts a new comment on the file.
 *
 * > Path parameters
 * key String
 * File to get comments from
 *
 * > Body parameters
 * message String
 * The text contents of the comment to post
 *
 * client_meta Vector2 | FrameOffset
 * The position of where to place the comment. This can either be an absolute canvas
 * position or the relative position within a frame.
 *
 * > Return value
 * The Comment that was successfully posted
 *
 * > Error codes
 * 404 The specified file was not found
 */
data class CommentRequest(
        @SerializedName(value = "client_meta")
        val clientMeta: ClientMeta,

        val message: String
)

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

data class Project(
        val id: Double,
        val name: String
)

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
)

data class DocumentWrapper(
        val document: Document,
        val components: Map<String, Component>? = null
)

data class ImageResponse(
        val err: String,
        val images: Map<String, String>,
        val status: Number
)
