package ru.kpfu.easyxml.modules.entities.properties

data class LayoutConstraint(var vertical: Constraint,
                            var horizontal: Constraint) {
    enum class Constraint {
        TOP,
        BOTTOM,
        CENTER,
        TOP_BOTTOM,
        LEFT_RIGHT,
        SCALE
    }
}