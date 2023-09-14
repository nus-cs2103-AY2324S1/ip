package gui;

import javafx.application.Application;

/**
 * The `Launcher` class serves as an entry point for launching the Fishron application.
 * It's used to work around classpath issues and start the JavaFX application.
 */
public class Launcher {
    /**
     * The main method to start the Fishron application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Launch the Fishron application using JavaFX Application.launch
        Application.launch(Fishron.class, args);
    }
}
