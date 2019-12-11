package io.github.jakejmattson.mousemeasure

import org.jnativehook.*
import org.jnativehook.mouse.*
import java.awt.Point
import java.util.logging.*

class MouseMeasure : NativeMouseInputListener {
    private val topLeft = TextDisplay(Location.TOP_LEFT)
    private val bottomRight = TextDisplay(Location.BOTTOM_RIGHT)

    override fun nativeMouseDragged(e: NativeMouseEvent) = updateFrame(bottomRight, e.point)
    override fun nativeMousePressed(e: NativeMouseEvent) = updateFrame(topLeft, e.point)

    override fun nativeMouseReleased(e: NativeMouseEvent) {
        topLeft.setVisible(false)
        bottomRight.setVisible(false)
    }

    override fun nativeMouseClicked(e: NativeMouseEvent) {}
    override fun nativeMouseMoved(e: NativeMouseEvent) {}

    private fun updateFrame(display: TextDisplay, location: Point) {
        display.setLocation(location)
        display.setText(pointToString(location))
        display.setVisible(true)
    }

    private fun pointToString(point: Point): String {
        return "X: " + point.x + " Y: " + point.y
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            disableLogging()
            val example = MouseMeasure()
            try {
                GlobalScreen.registerNativeHook()
                GlobalScreen.addNativeMouseListener(example)
                GlobalScreen.addNativeMouseMotionListener(example)
            } catch (e: NativeHookException) {
                e.printStackTrace()
            }
        }

        private fun disableLogging() {
            val logger = Logger.getLogger(GlobalScreen::class.java.getPackage().name)
            logger.level = Level.WARNING
            logger.useParentHandlers = false
        }
    }
}