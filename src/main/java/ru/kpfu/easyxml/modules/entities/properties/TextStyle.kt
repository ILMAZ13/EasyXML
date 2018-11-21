package ru.kpfu.easyxml.modules.entities.properties

data class TextStyle(var fontFamily: String,
                     var italic: Boolean,
                     var fontSize: Number,
                     var textAlignHorizontal: Alignment,
                     var textAlignVertical: Alignment,
                     var letterSpacing: Number) {
    enum class Alignment {
        LEFT,
        RIGHT,
        CENTER,
        JUSTIFIED,
        TOP,
        BOTTOM
    }
}