package ru.kpfu.easyxml.modules.recognition

import ru.kpfu.easyxml.modules.entities.Color
import ru.kpfu.easyxml.modules.entities.Document
import ru.kpfu.easyxml.modules.entities.NodeType
import ru.kpfu.easyxml.modules.entities.ui_elements.*
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class NameBasedRecognizer : Recognizer {

    override fun recognize(document: Document): Screen {
        val screen = recognizeScreen(document)
        document.children?.forEach {
            recognizeDocument(it, screen)
        }
        return screen
    }

    private fun recognizeDocument(document: Document, parentView: View) {
        when (parentView) {
            is Screen -> {
                if (isBackground(document)) {

                } else {
                    recursiveRecognition(document, parentView)
                }
            }

            is Button -> {
                println(document.type)
            }

            is CardView -> {
                if (isBackground(document)) {
                    if (!document.effects.isNullOrEmpty())
                        parentView.cardCornerRadius = document.effects[0].radius
                    if (!document.fills.isNullOrEmpty())
                        parentView.backgroundColor = document.fills[0].color
                } else {
                    recursiveRecognition(document, parentView)
                }
            }

            is StatusBar -> {
                println(document.type)
            }

            is AppBar -> {
                println(document.type)
            }
        }
    }

    private fun recursiveRecognition(document: Document, parentView: ViewGroup) {
        val childView = recognizeView(document)
        childView?.let { view ->
            parentView.children.add(view)
            document.children?.forEach {
                recognizeDocument(it, view)
            }
        }
    }

    private fun recognizeScreen(document: Document): Screen {
        return Screen(document)
    }

    private fun recognizeView(document: Document): View? {
        var view: View? = null

        if (document.visible != null && !document.visible)
            return null

        if (document.exportSettings?.isNotEmpty() == true)
            return Icon(document)

        if (document.type == NodeType.TEXT)
            return TextView(document)

        document.name?.let {
            view = when {
                it.contains(CheckBox.KEY, true) -> CheckBox(document)
                it.contains(AppBar.KEY, true) -> AppBar(document)
                it.contains(StatusBar.KEY, true) -> StatusBar(document)
                it.contains(Button.KEY, true) -> Button(document)
                it.contains(CardView.KEY, true) -> CardView(document)
                else -> {
                    null
                }
            }
        }
        return view
    }

    private fun isBackground(document: Document): Boolean =
            (document.type == NodeType.VECTOR || document.exportSettings?.isEmpty() == true)
                    && (document.visible == null || document.visible)
                    && (document.children.isNullOrEmpty())
                    && (!document.fills.isNullOrEmpty() || !document.effects.isNullOrEmpty())
}
