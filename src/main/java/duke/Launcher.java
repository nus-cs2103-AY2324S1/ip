package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the application.
     * @param args Argument strings (not used)
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
