package io.github.jakejmattson.mousemeasure;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseMeasure implements NativeMouseInputListener {

    private Point origin;
    private TextDisplay topLeft = new TextDisplay();
    private TextDisplay bottomRight = new TextDisplay();

    public static void main(String[] args)
    {
        disableLogging();
        MouseMeasure example = new MouseMeasure();

        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(example);
            GlobalScreen.addNativeMouseMotionListener(example);
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
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
        Point current = e.getPoint();
        bottomRight.setLocation(current);
        bottomRight.setText("X: " + e.getX() + " Y: " + e.getY());
        bottomRight.setVisible(true);
    }

    public void nativeMousePressed(NativeMouseEvent e)
    {
        origin = e.getPoint();
        topLeft.setLocation(origin);
        topLeft.setText("X: " + e.getX() + " Y: " + e.getY());
        topLeft.setVisible(true);
    }

    public void nativeMouseReleased(NativeMouseEvent e)
    {
        topLeft.setVisible(false);
        bottomRight.setVisible(false);
    }

    public void nativeMouseClicked(NativeMouseEvent e) {}
    public void nativeMouseMoved(NativeMouseEvent e) {}
}