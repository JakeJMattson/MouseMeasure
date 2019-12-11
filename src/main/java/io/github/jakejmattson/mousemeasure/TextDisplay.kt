package io.github.jakejmattson.mousemeasure

import java.awt.Point
import javax.swing.*

class TextDisplay(relativeLocation: Location) {
    private val frame: JFrame
    private val lblText: JLabel
    private val relativeLocation: Location

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

    fun setText(text: String?) {
        lblText.text = text
    }

    fun setVisible(visible: Boolean) {
        frame.isVisible = visible
    }

    init {
        frame = JFrame()
        lblText = JLabel("Coordinates")
        this.relativeLocation = relativeLocation
        frame.isAlwaysOnTop = true
        frame.isUndecorated = true
        frame.isAutoRequestFocus = false
        val panel = JPanel()
        panel.add(lblText)
        frame.add(panel)
        frame.pack()
    }
}