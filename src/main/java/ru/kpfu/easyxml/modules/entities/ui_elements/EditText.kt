package ru.kpfu.easyxml.modules.entities.ui_elements

import ru.kpfu.easyxml.modules.entities.figma.Document

class EditText(document: Document) : TextView(document) {
    companion object {
        const val KEY = "edit_text"
    }

    override fun getParamLines(list: MutableList<String>, isParent: Boolean): MutableList<String> {
        if (isParent)
            list.add("<EditText")
        super.getParamLines(list, false)
        if (isParent)
            list.add("/>")

        return list
    }
}
