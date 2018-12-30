package io.github.jakejmattson.mousemeasure;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseMeasure implements NativeMouseInputListener
{
    private TextDisplay topLeft = new TextDisplay();
    private TextDisplay bottomRight = new TextDisplay();

    public static void main(String[] args)
    {
        disableLogging();
        MouseMeasure example = new MouseMeasure();

        try
        {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(example);
            GlobalScreen.addNativeMouseMotionListener(example);
        }
        catch (NativeHookException e)
        {
            e.printStackTrace();
        }
    }

    private static void disableLogging()
    {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
    }

    public void nativeMouseDragged(NativeMouseEvent e)
    {
        updateFrame(bottomRight, e.getPoint());
    }

    public void nativeMousePressed(NativeMouseEvent e)
    {
        updateFrame(topLeft, e.getPoint());
    }

    public void nativeMouseReleased(NativeMouseEvent e)
    {
        topLeft.setVisible(false);
        bottomRight.setVisible(false);
    }

    public void nativeMouseClicked(NativeMouseEvent e) {}
    public void nativeMouseMoved(NativeMouseEvent e) {}

    private void updateFrame(TextDisplay display, Point location)
    {
        display.setLocation(location);
        display.setText(pointToString(location));
        display.setVisible(true);
    }

    private String pointToString(Point point)
    {
        return "X: " + point.x + " Y: " + point.y;
    }
}