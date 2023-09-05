package anto;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to run launcher.
     *
     * @param args String input.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
