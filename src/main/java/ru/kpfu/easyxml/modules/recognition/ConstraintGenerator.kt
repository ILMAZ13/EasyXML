package ru.kpfu.easyxml.modules.recognition

import ru.kpfu.easyxml.modules.entities.figma.Document
import ru.kpfu.easyxml.modules.entities.figma.LayoutConstraint
import ru.kpfu.easyxml.modules.entities.ui_elements.Screen
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class ConstraintGenerator {
    fun generateConstraint(screen: Screen) {
        screen.layoutWidth = View.MATCH_PARENT
        screen.layoutHeight = View.MATCH_PARENT
        generateConstraintOnGroup(screen)
    }

    fun fixConstraints(document: Document) {
        if (!document.children.isNullOrEmpty()) {
            document.children.forEach {
                fixConstraints(it)
            }
            var vertical = document.children[0].constraints.vertical
            var horizontal = document.children[0].constraints.horizontal
            document.children.forEach {
                if (vertical != it.constraints.vertical)
                    vertical = LayoutConstraint.Vertical.Mixed
                if (horizontal != it.constraints.horizontal)
                    horizontal = LayoutConstraint.Horizontal.Mixed
            }
            document.constraints = LayoutConstraint(horizontal, vertical)
        }
    }

    private fun generateConstraintOnGroup(viewGroup: ViewGroup) {
        //to top
        val topList = viewGroup.children.filter { it.document.constraints.vertical == LayoutConstraint.Vertical.Top }
                .sortedBy { it.y }
        topList.forEachIndexed { index, view ->
            var i = index - 1
            while (true) {
                if (i >= 0) {
                    val topView = topList[i]
                    val marginTop = view.y - topView.y - topView.height
                    if (marginTop < 0) {
                        i--
                    } else {
                        view.marginTop = marginTop
                        view.constraintTop =
                                if (topView.isShown())
                                    topView.id
                                else
                                    topView.constraintTop
                        break
                    }
                } else {
                    view.constraintTop = "parent"
                    view.marginTop = view.y
                    break
                }
            }
            view.layoutHeight = View.WRAP_CONTENT
        }
        //to bottom
        val bottomList = viewGroup.children.filter { it.document.constraints.vertical == LayoutConstraint.Vertical.Bottom }
                .sortedByDescending { it.y }
        bottomList.forEachIndexed { index, view ->
            var i = index - 1
            while (true) {
                if (i >= 0) {
                    val bottomView = bottomList[i]
                    val marginBottom = bottomView.y - view.y - view.height
                    if (marginBottom < 0) {
                        i--
                    } else {
                        view.marginBottom = marginBottom
                        view.constraintBottom =
                                if (bottomView.isShown())
                                    bottomView.id
                                else
                                    bottomView.constraintBottom
                        break
                    }
                } else {
                    view.constraintBottom = "parent"
                    view.marginBottom = viewGroup.height - view.y - view.height
                    break
                }
            }
            view.layoutHeight = View.WRAP_CONTENT
        }
        //to left
        val leftList = viewGroup.children.filter { it.document.constraints.horizontal == LayoutConstraint.Horizontal.Left }
                .sortedBy { it.x }
        leftList.forEachIndexed { index, view ->
            var i = index - 1
            while (true) {
                if (i >= 0) {
                    val leftView = leftList[i]
                    val marginLeft = view.x - leftView.x - leftView.width
                    if (marginLeft < 0) {
                        i--
                    } else {
                        view.marginStart = marginLeft
                        view.constraintStart =
                                if (leftView.isShown())
                                    leftView.id
                                else
                                    leftView.constraintStart
                        break
                    }
                } else {
                    view.constraintStart = "parent"
                    view.marginStart = view.x
                    break
                }
            }
            view.layoutWidth = View.WRAP_CONTENT
        }
        //to right
        val rightList = viewGroup.children.filter { it.document.constraints.horizontal == LayoutConstraint.Horizontal.Right }
                .sortedByDescending { it.x }
        rightList.forEachIndexed { index, view ->
            var i = index - 1
            while (true) {
                if (i >= 0) {
                    val rightView = rightList[i]
                    val marginRight = rightView.x - view.x - view.width
                    if (marginRight < 0) {
                        i--
                    } else {
                        view.marginEnd = marginRight
                        view.constraintEnd =
                                if (rightView.isShown())
                                    rightView.id
                                else
                                    rightView.constraintEnd
                        break
                    }
                } else {
                    view.marginEnd = viewGroup.width - view.x - view.width
                    view.constraintEnd = "parent"
                    break
                }
            }
            view.layoutWidth = View.WRAP_CONTENT
        }

        //todo add center, leftRight, topBottom, guidelines
        viewGroup.children.filter { it.document.constraints.horizontal == LayoutConstraint.Horizontal.LeftRight }
                .forEach {
                    it.constraintStart = "parent"
                    it.constraintEnd = "parent"
                    it.marginStart = it.x
                    it.marginEnd = viewGroup.width - it.x - it.width
                    it.layoutWidth = View.MATCH_PARENT
                }

        viewGroup.children.filter { it.document.constraints.vertical == LayoutConstraint.Vertical.TopBottom }
                .forEach {
                    it.constraintTop = "parent"
                    it.constraintBottom = "parent"
                    it.marginTop = it.y
                    it.marginBottom = viewGroup.height - it.y - it.height
                    it.layoutHeight = View.MATCH_PARENT
                }

        //recursion to other layouts
        viewGroup.children.forEach {
            if (it is ViewGroup)
                generateConstraintOnGroup(it)
        }
    }
}
