package io.github.jakejmattson.mousemeasure;

import javax.swing.*;
import java.awt.*;

public class TextDisplay {

    private JFrame frame;
    private JLabel lblText;

    public TextDisplay()
    {
        frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        frame.setAutoRequestFocus(false);

        lblText = new JLabel("Coordinates");
        JPanel panel = new JPanel();
        panel.add(lblText);

        frame.add(panel);
        frame.pack();
    }

    public void setLocation(Point location)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        Rectangle frameBounds = frame.getBounds();
        double frameHeight = frameBounds.getHeight();
        double frameWidth = frameBounds.getWidth();

        int rightBound = (int)(location.getX() + frameWidth);
        int lowerBound = (int)(location.getY() + frameHeight);

        final int BUFFER = 10;

        if (rightBound >= screenWidth)
            location.x -= frameWidth + BUFFER;
        else
            location.x += BUFFER;

        if (lowerBound >= screenHeight)
            location.y -= frameHeight + BUFFER;
        else
            location.y += BUFFER;

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
