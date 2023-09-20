package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues when starting the Duke application.
 */
public class Launcher {
    /**
     * Main method to launch the Duke application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
