package ru.kpfu.easyxml.modules.entities.properties

data class Constraint(var type: Type,
                 var value: Number) {

    enum class Type {
        SCALE, WIDTH, HEIGHT
    }
}