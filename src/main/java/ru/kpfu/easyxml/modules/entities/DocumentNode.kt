package ru.kpfu.easyxml.modules.entities

class DocumentNode (
        override var id: String,
        override var name: String,
        override var visible: Boolean,
        var children: List<Node>
): Node() {
    override var type = "DOCUMENT"
}