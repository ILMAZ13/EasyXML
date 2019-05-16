package ru.kpfu.easyxml.modules.recognition

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.figma.NodeType
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
                    setBackgroundToParent(document, parentView)
                } else {
                    recursiveRecognition(document, parentView)
                }
            }

            is Button -> {
                when {
                    isBackground(document) -> setBackgroundToParent(document, parentView)
                    recognizeView(document) is TextView -> {
                        parentView.text = document.characters ?: parentView.text
                        parentView.textStyle = document.style
                    }
                }
            }

            is CardView -> {
                if (isBackground(document)) {
                    setBackgroundToParent(document, parentView)
                } else {
                    recursiveRecognition(document, parentView)
                }
            }

            is StatusBar -> {
                if (isBackground(document))
                    setBackgroundToParent(document, parentView)
            }

            is AppBar -> {
                if (isBackground(document))
                    setBackgroundToParent(document, parentView)
            }

            is ViewGroup -> {
                if (isBackground(document)) {
                    setBackgroundToParent(document, parentView)
                } else {
                    recursiveRecognition(document, parentView)
                }
            }
        }
    }

    private fun recognizeScreen(document: Document): Screen {
        return Screen(document)
    }

    private fun recursiveRecognition(document: Document, parentView: ViewGroup) {
        val childView = recognizeView(document)
        childView?.let { view ->
            setCoordinates(view, parentView)
            parentView.children.add(view)
            if (isNeedToRecognizeChildren(view)) {
                document.children?.forEach {
                    recognizeDocument(it, view)
                }
            }
        }
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
        if (view == null && (document.type == NodeType.GROUP || document.type == NodeType.INSTANCE))
            view = ViewGroup(document)
        return view
    }

    private fun isNeedToRecognizeChildren(view: View): Boolean =
            view !is Icon

    private fun isBackground(document: Document): Boolean =
            (document.type == NodeType.VECTOR || document.type == NodeType.RECTANGLE || document.type == NodeType.ELLIPSE)
                    && document.exportSettings?.isEmpty() == true
                    && (document.visible == null || document.visible)
                    && (document.children.isNullOrEmpty())
                    && (!document.fills.isNullOrEmpty() || !document.effects.isNullOrEmpty())

    private fun setBackgroundToParent(document: Document, parentView: View) {
        if (!document.fills.isNullOrEmpty())
            parentView.backgroundColor = document.fills[0].color
        //ToDo: Need to fix
        if (!document.effects.isNullOrEmpty())
            parentView.radius = document.effects[0].radius ?: parentView.radius
    }

    private fun setCoordinates(view: View, parentView: View) {
        view.x = view.absoluteX - parentView.absoluteX
        view.y = view.absoluteY - parentView.absoluteY
    }
}
