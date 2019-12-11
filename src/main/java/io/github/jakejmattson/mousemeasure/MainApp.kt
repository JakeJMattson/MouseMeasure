package io.github.jakejmattson.mousemeasure

import org.jnativehook.GlobalScreen
import java.util.logging.*

fun main() {
    disableLogging()
    val measurer = MouseMeasure()

    GlobalScreen.registerNativeHook()
    GlobalScreen.addNativeMouseListener(measurer)
    GlobalScreen.addNativeMouseMotionListener(measurer)
}

private fun disableLogging() {
    Logger.getLogger(GlobalScreen::class.java.getPackage().name).apply {
        level = Level.WARNING
        useParentHandlers = false
    }
}