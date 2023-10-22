package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Exits the application.
     */
    public static void exit() {
        System.exit(0);
    }
}
