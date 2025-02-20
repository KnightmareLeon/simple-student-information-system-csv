package main.app.errors;

import javax.swing.JComponent;

/**
 * Interface to start a {@code JOptionDialog} that
 * will send an error message to the user
 * */
public interface ErrorWindow {
    public void startErrorWindow(JComponent component);
}
