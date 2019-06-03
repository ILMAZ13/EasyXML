package ru.kpfu.easyxml.modules.recognition

import ru.kpfu.easyxml.modules.entities.figma.LayoutConstraint
import ru.kpfu.easyxml.modules.entities.ui_elements.Screen
import ru.kpfu.easyxml.modules.entities.ui_elements.base.View
import ru.kpfu.easyxml.modules.entities.ui_elements.base.ViewGroup

class ConstraintGenerator {
    fun generateConstraint(screen: Screen) {
        //todo add constraints on screen too
        generateConstraintOnGroup(screen)
    }

    private fun generateConstraintOnGroup(viewGroup: ViewGroup) {
        //to top
        var previousId = "parent"
        var previousBottomY = 0.0
        viewGroup.children.filter { it.document.constraints?.vertical == LayoutConstraint.Vertical.Top }
                .sortedBy { it.y }
                .forEach {
                    it.constraintTop = previousId
                    it.marginTop = it.y - previousBottomY
                    previousId = it.id
                    previousBottomY = it.y + it.height
                    it.layoutHeight = View.WRAP_CONTENT
                }
        //to bottom
        previousId = "parent"
        var previousTopY = viewGroup.height
        viewGroup.children.filter { it.document.constraints?.vertical == LayoutConstraint.Vertical.Bottom }
                .sortedByDescending { it.y }
                .forEach {
                    it.constraintBottom = previousId
                    it.marginBottom = previousTopY - (it.y + it.height)
                    previousId = it.id
                    previousTopY = it.y
                    it.layoutHeight = View.WRAP_CONTENT
                }
        //to left
        previousId = "parent"
        var previousRightX = 0.0
        viewGroup.children.filter { it.document.constraints?.horizontal == LayoutConstraint.Horizontal.Left }
                .sortedBy { it.x }
                .forEach {
                    it.constraintStart = previousId
                    it.marginStart = it.x - previousRightX
                    previousId = it.id
                    previousRightX = it.x + it.width
                    it.layoutWidth = View.WRAP_CONTENT
                }
        //to right
        previousId = "parent"
        var previousLeftX = viewGroup.width
        viewGroup.children.filter { it.document.constraints?.horizontal == LayoutConstraint.Horizontal.Right }
                .sortedByDescending { it.x }
                .forEach {
                    it.constraintEnd = previousId
                    it.marginEnd = previousLeftX - (it.x + it.width)
                    previousId = it.id
                    previousLeftX = it.x
                    it.layoutWidth = View.WRAP_CONTENT
                }

        //todo add center, leftRight, topBottom, guidelines

        //recursion to other layouts
        viewGroup.children.forEach {
            if (it is ViewGroup)
                generateConstraintOnGroup(it)
        }
    }
}
