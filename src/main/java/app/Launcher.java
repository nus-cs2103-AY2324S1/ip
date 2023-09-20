package app;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * Adapted from CS2103 website on JavaFX (https://se-education.org/guides/tutorials/javaFx.html)
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Launcher {
    /**
     * The main entry point for launching the JavaFX application.
     *
     * @param args Command-line arguments (if any) to be passed to the JavaFX application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
