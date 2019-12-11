package io.github.jakejmattson.mousemeasure

import java.awt.Point
import javax.swing.*

class TextDisplay(private val relativeLocation: Location) {
    private val lblText: JLabel = JLabel("Coordinates")

    private val frame: JFrame = JFrame().apply {
        isAlwaysOnTop = true
        isUndecorated = true
        isAutoRequestFocus = false
        val panel = JPanel()
        panel.add(lblText)
        add(panel)
        pack()
    }

    fun setLocation(location: Point) {
        val frameBounds = frame.bounds
        val frameHeight = frameBounds.getHeight().toInt()
        val frameWidth = frameBounds.getWidth().toInt()
        val BUFFER = 10

        if (relativeLocation == Location.TOP_LEFT) {
            location.x += BUFFER
            location.y += BUFFER
        } else if (relativeLocation == Location.BOTTOM_RIGHT) {
            location.x -= frameWidth + BUFFER
            location.y -= frameHeight + BUFFER
        }

        frame.location = location
    }

    fun setText(text: String) {
        lblText.text = text
    }

    fun setVisible(visible: Boolean) {
        frame.isVisible = visible
    }
}