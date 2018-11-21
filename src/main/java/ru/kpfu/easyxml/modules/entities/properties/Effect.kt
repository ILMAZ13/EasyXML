package ru.kpfu.easyxml.modules.entities.properties

data class Effect(var type: Type,
                  var visible: Boolean,
                  var radius: Number) {
    enum class Type {
        INNER_SHADOW,
        DROP_SHADOW,
        LAYER_BLUR,
        BACKGROUND_BLUR
    }
}