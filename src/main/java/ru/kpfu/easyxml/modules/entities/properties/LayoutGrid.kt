package ru.kpfu.easyxml.modules.entities.properties

data class LayoutGrid(var pattern: Pattern,
                      var sectionSize: Number,
                      var visible: Boolean,
                      var color: Color,
                      var alignment: Alignment,
                      var gutterSize: Number,
                      var offset: Number,
                      var count: Number) {

    enum class Pattern {
        COLUMNS,
        ROWS,
        GRID
    }

    enum class Alignment {
        COLUMNS,
        ROWS,
        GRID
    }
}