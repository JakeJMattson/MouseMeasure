package io.github.jakejmattson.mousemeasure;

import javax.swing.*;
import java.awt.*;

public class TextDisplay {

    private JFrame frame;
    private JLabel lblText;
    private Location relativeLocation;

    public TextDisplay(Location relativeLocation)
    {
        this.frame = new JFrame();
        this.lblText = new JLabel("Coordinates");
        this.relativeLocation = relativeLocation;

        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        frame.setAutoRequestFocus(false);

        JPanel panel = new JPanel();
        panel.add(lblText);

        frame.add(panel);
        frame.pack();
    }

    public void setLocation(Point location)
    {
        Rectangle frameBounds = frame.getBounds();
        double frameHeight = frameBounds.getHeight();
        double frameWidth = frameBounds.getWidth();

        final int BUFFER = 10;

        if (relativeLocation == Location.TOP_LEFT)
        {
            location.x += BUFFER;
            location.y += BUFFER;
        }
        else if (relativeLocation == Location.BOTTOM_RIGHT)
        {
            location.x -= frameWidth + BUFFER;
            location.y -= frameHeight + BUFFER;
        }

        frame.setLocation(location);
    }

    public void setText(String text)
    {
        lblText.setText(text);
    }

    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }
}
