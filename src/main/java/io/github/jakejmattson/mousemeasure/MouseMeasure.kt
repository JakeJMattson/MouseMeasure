package io.github.jakejmattson.mousemeasure

import org.jnativehook.mouse.*
import java.awt.Point

class MouseMeasure : NativeMouseInputListener {
    private val topLeft = TextDisplay(Location.TOP_LEFT)
    private val bottomRight = TextDisplay(Location.BOTTOM_RIGHT)

    override fun nativeMouseDragged(e: NativeMouseEvent) = updateFrame(bottomRight, e.point)
    override fun nativeMousePressed(e: NativeMouseEvent) = updateFrame(topLeft, e.point)

    override fun nativeMouseReleased(e: NativeMouseEvent) {
        topLeft.setVisible(false)
        bottomRight.setVisible(false)
    }

    override fun nativeMouseClicked(e: NativeMouseEvent) = Unit
    override fun nativeMouseMoved(e: NativeMouseEvent) = Unit

    private fun updateFrame(display: TextDisplay, location: Point) {
        display.setLocation(location)
        display.setText(pointToString(location))
        display.setVisible(true)
    }

    private fun pointToString(point: Point) = "X: ${point.x} Y: ${point.y}"
}