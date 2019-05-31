package ru.kpfu.easyxml.modules.recognition

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.figma.Effect
import ru.kpfu.easyxml.modules.entities.figma.NodeType
import ru.kpfu.easyxml.modules.entities.figma.Paint
import ru.kpfu.easyxml.modules.entities.ui_elements.*
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class NameBasedRecognizer : Recognizer {

    companion object {
        private const val THRESHOLD = 8
    }

    private var results: List<ObjectDetector.Result>? = null
    private lateinit var screen: Screen

    override fun recognize(document: Document, results: List<ObjectDetector.Result>): Screen {
        this.results = results
        screen = recognizeScreen(document)
        document.children?.forEachIndexed { index, document ->
            recognizeDocument(document, index == 0, screen)
        }
        return screen
    }

    private fun recognizeDocument(document: Document, isFirst: Boolean, parentView: View) {
        when (parentView) {
            is Screen -> {
                if (isBackground(document, isFirst)) {
                    setBackgroundToParent(document, parentView)
                } else {
                    recursiveRecognition(document, parentView)
                }
            }

            is Button -> {
                when {
                    isBackground(document, isFirst) -> setBackgroundToParent(document, parentView)
                    recognizeView(document) is TextView -> {
                        parentView.text = document.characters ?: parentView.text
                        parentView.textStyle = document.style
                    }
                }
            }

            is EditText -> {
                when {
                    isBackground(document, isFirst) -> setBackgroundToParent(document, parentView)
                    recognizeView(document) is TextView -> {
                        parentView.text = document.characters ?: parentView.text
                        parentView.textStyle = document.style
                    }
                }
            }

            is CardView -> {
                if (isBackground(document, isFirst)) {
                    setBackgroundToParent(document, parentView)
                } else {
                    recursiveRecognition(document, parentView)
                }
            }

            //todo add fab

            is AppBar -> {
                if (isBackground(document, isFirst))
                    setBackgroundToParent(document, parentView)
            }

            is ViewGroup -> {
                if (isBackground(document, isFirst)) {
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
                document.children?.forEachIndexed { index, document ->
                    recognizeDocument(document, index == 0, view)
                }
            }
        }
    }

    private fun recognizeView(document: Document): View? {
        var view: View?

        if (!document.visible)
            return null

        if (document.type == NodeType.TEXT)
            return TextView(document)

        view = getDetectedView(document)

        if (view == null)
            view = when {
                isCardView(document) -> CardView(document)
                isIcon(document) -> Icon(document)
                document.type == NodeType.GROUP || document.type == NodeType.INSTANCE -> ViewGroup(document)
                else -> null
            }

        return view
    }

    private fun getDetectedView(document: Document): View? {
        document.absoluteBoundingBox?.let {
            results?.forEach { result ->
                if (it.x - screen.absoluteX + THRESHOLD > result.x * screen.width
                        && it.y - screen.absoluteY + THRESHOLD > result.y * screen.height
                        && it.x - screen.absoluteX + it.width - THRESHOLD < result.x2 * screen.width
                        && it.y - screen.absoluteY + it.height - THRESHOLD < result.y2 * screen.height) {
                    when (result.name) {
                        AppBar.KEY -> AppBar(document)
                        BottomNavigation.KEY -> BottomNavigation(document)
                        Button.KEY -> Button(document)
                        CheckBox.KEY -> CheckBox(document)
                        EditText.KEY -> EditText(document)
                        FloatingActionButton.KEY -> FloatingActionButton(document)
                        Keyboard.KEY -> Keyboard(document)
                        NavBar.KEY -> NavBar(document)
                        RadioButton.KEY -> RadioButton(document)
                        SeekBar.KEY -> SeekBar(document)
                        StatusBar.KEY -> StatusBar(document)
                        Switch.KEY -> Switch(document)
                        else -> null
                    }?.let {
                        return it
                    }
                }
            }
        }
        return null
    }

    private fun isNeedToRecognizeChildren(view: View): Boolean =
            view !is Icon
                    && view !is Keyboard
                    && view !is StatusBar
                    && view !is NavBar
                    && view !is BottomNavigation

    private fun isBackground(document: Document, isFirst: Boolean): Boolean =
            document.type == NodeType.RECTANGLE
                    && isFirst
                    && document.visible
                    && document.children.isNullOrEmpty()
                    && !document.fills.isNullOrEmpty()
                    && document.fills[0].type == Paint.FillType.Solid

    private fun isCardView(document: Document): Boolean =
            document.type == NodeType.GROUP
                    && document.children != null
                    && document.children.size > 1
                    && isBackground(document.children[0], true)
                    && document.children[0].cornerRadius != null
                    && document.children[0].cornerRadius!! > 0.0
                    && hasShadow(document.children[0])

    private fun isIcon(document: Document): Boolean {
        if (document.type == NodeType.VECTOR
                || document.type == NodeType.RECTANGLE
                || document.type == NodeType.ELLIPSE)
            return true

        if (document.children.isNullOrEmpty()) {
            return false
        } else {
            document.children.forEach {
                if (it.type != NodeType.VECTOR
                        && it.type != NodeType.RECTANGLE
                        && it.type != NodeType.ELLIPSE)
                    return false
            }
            return true
        }
    }

    private fun hasShadow(document: Document): Boolean {
        document.effects?.forEach {
            if (it.visible && it.type == Effect.EffectType.DropShadow)
                return true
        }
        return false
    }

    private fun setBackgroundToParent(document: Document, parentView: View) {
        if (!document.fills.isNullOrEmpty())
            parentView.backgroundColor = document.fills[0].color
        //ToDo: Need to fix
        parentView.radius = document.cornerRadius ?: 0.0
    }

    private fun setCoordinates(view: View, parentView: View) {
        view.x = view.absoluteX - parentView.absoluteX
        view.y = view.absoluteY - parentView.absoluteY
    }
}
