package gui;

import javafx.application.Application;

/**
 * The `Launcher` class serves as an entry point for launching the Fishron application.
 * It's used to work around classpath issues and start the JavaFX application.
 */
public class Launcher {
    /**
     * Launches the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
