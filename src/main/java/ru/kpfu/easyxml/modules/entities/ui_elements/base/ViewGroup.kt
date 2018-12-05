package ru.kpfu.easyxml.modules.entities.ui_elements.base

import ru.kpfu.easyxml.modules.entities.Document

open class ViewGroup(document: Document) : View(document) {
    var children: MutableList<View> = ArrayList()
}