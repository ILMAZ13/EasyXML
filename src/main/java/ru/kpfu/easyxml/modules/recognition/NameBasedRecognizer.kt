package ru.kpfu.easyxml.modules.recognition

import ru.kpfu.easyxml.modules.entities.figma.*
import ru.kpfu.easyxml.modules.entities.ui_elements.*
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class NameBasedRecognizer : Recognizer {

    companion object {
        private const val THRESHOLD = 16
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
                    recognizeView(document, null) is TextView -> {
                        parentView.text = document.characters ?: parentView.text
                        parentView.textStyle = document.style
                        document.fills?.forEach {
                            if (it.visible && it.type == Paint.FillType.Solid)
                                it.color?.apply {
                                    a *= document.opacity
                                }?.let {
                                    parentView.textColor = it
                                }
                        }
                    }
                }
            }

            is EditText -> {
                when {
                    isBackground(document, isFirst) -> setBackgroundToParent(document, parentView)
                    recognizeView(document, null) is TextView -> {
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

            is Switch -> {
                var color: Color? = null
                document.fills?.forEach {
                    if (it.visible && it.type == Paint.FillType.Solid)
                        color = it.color?.apply {
                            a *= document.opacity
                        }
                }
                color?.let {
                    parentView.trackTint = parentView.thumbTint
                    parentView.thumbTint = it
                }
            }

            is RadioButton -> {
                document.fills?.forEach {
                    if (it.visible && it.type == Paint.FillType.Solid)
                        it.color?.apply {
                            a *= document.opacity
                        }?.let {
                            parentView.backgroundColor = it
                        }
                }
            }

            is CheckBox -> {
                document.fills?.forEach {
                    if (it.visible && it.type == Paint.FillType.Solid)
                        it.color?.apply {
                            a *= document.opacity
                        }?.let {
                            parentView.backgroundColor = it
                        }
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
                    recursiveRecognition(document, parentView.parent ?: parentView)
                }
            }
        }
    }

    private fun recognizeScreen(document: Document): Screen {
        return Screen(document)
    }

    private fun recursiveRecognition(document: Document, parentView: ViewGroup) {
        val childView = recognizeView(document, parentView)
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

    private fun recognizeView(document: Document, parentView: ViewGroup?): View? {
        var view: View?

        if (!document.visible)
            return null

        if (document.type == NodeType.TEXT)
            return TextView(document)

        view = getDetectedView(document)

        if (view == null)
            view = when {
                isCardView(document) -> CardView(document, parentView)
                isImage(document) -> ImageView(document)
                document.type == NodeType.GROUP || document.type == NodeType.INSTANCE -> ViewGroup(document, parentView)
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
            view !is ImageView
                    && view !is Keyboard
                    && view !is StatusBar
                    && view !is NavBar
                    && view !is BottomNavigation

    private fun isBackground(document: Document, isFirst: Boolean): Boolean =
            (document.type == NodeType.RECTANGLE
                    || document.type == NodeType.VECTOR
                    || document.type == NodeType.ELLIPSE)
                    && isFirst
                    && document.visible
                    && document.children.isNullOrEmpty()
                    && hasSolidFill(document)

    private fun isCardView(document: Document): Boolean =
            document.type == NodeType.GROUP
                    && document.children != null
                    && document.children.size > 1
                    && isBackground(document.children[0], true)
                    && document.children[0].cornerRadius != null
                    && document.children[0].cornerRadius!! > 0.0
                    && hasShadow(document.children[0])

    private fun isImage(document: Document): Boolean {
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
        document.fills?.forEach {
            if (it.visible && it.type == Paint.FillType.Solid)
                parentView.backgroundColor = it.color?.apply {
                    a *= document.opacity
                }
        }
        //ToDo: Need to fix
        parentView.radius = document.cornerRadius ?: 0.0
    }

    private fun hasSolidFill(document: Document): Boolean {
        document.fills?.forEach {
            if (it.visible && it.type == Paint.FillType.Solid)
                return true
        }
        return false
    }

    private fun setCoordinates(view: View, parentView: View) {
        view.x = view.absoluteX - parentView.absoluteX
        view.y = view.absoluteY - parentView.absoluteY
    }
}
