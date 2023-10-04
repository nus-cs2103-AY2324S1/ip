package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs the GUI Duke program.
     *
     * @param args The collection of String arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
