package ru.kpfu.easyxml.modules.entities

abstract class Node() {
    abstract var id: String
    abstract var name: String
    abstract var visible: Boolean
    abstract val type: String
}