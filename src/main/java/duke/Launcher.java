package duke;

import javafx.application.Application;

/**
 * The Launcher class serves as the entry point for launching the Duke application. It is used to work around
 * classpath issues when starting a JavaFX application.
 * <p>
 * Credit to: https://se-education.org/guides/tutorials/javaFx.html
 */
public class Launcher {
    /**
     * The main method of the Launcher class, which launches the Duke application by calling the `Application.launch` method.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        UiGUI uiGUI;
        Application.launch(Duke.class, args);
    }
}