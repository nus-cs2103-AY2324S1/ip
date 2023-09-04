package duke.controller;

import duke.duke.Main;
import javafx.application.Application;

/**
 * Represents the JavaFX Application Launcher.
 */
public class Launcher {

    /**
     * A launcher class to workaround classpath issues.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
