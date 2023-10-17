package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the application.
     * @param args The arguments to be passed into the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
