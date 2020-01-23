package me.jakejmattson.mousemeasure

import org.jnativehook.mouse.*
import java.awt.Point

class MouseMeasure : NativeMouseInputListener {
    private val topLeft = TextDisplay(Location.TOP_LEFT)
    private val bottomRight = TextDisplay(Location.BOTTOM_RIGHT)

    override fun nativeMouseDragged(e: NativeMouseEvent) = bottomRight.update(e.point)
    override fun nativeMousePressed(e: NativeMouseEvent) = topLeft.update(e.point)

    override fun nativeMouseReleased(e: NativeMouseEvent) {
        topLeft.setVisible(false)
        bottomRight.setVisible(false)
    }

    override fun nativeMouseClicked(e: NativeMouseEvent) = Unit
    override fun nativeMouseMoved(e: NativeMouseEvent) = Unit

    private fun TextDisplay.update(location: Point) {
        setLocation(location)
        setText(location.toDisplayString())
        setVisible(true)
    }

    private fun Point.toDisplayString() = "X: $x Y: $y"
}