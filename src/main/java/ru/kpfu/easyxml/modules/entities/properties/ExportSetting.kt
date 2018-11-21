package ru.kpfu.easyxml.modules.entities.properties

data class ExportSetting(var suffix: String,
                         var format: Format,
                         var constraint: Constraint) {
    enum class Format {
        JPG, PNG, SVG
    }
}