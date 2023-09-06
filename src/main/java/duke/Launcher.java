package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main entry point of the application.
     * @param args String arguments to be taken in
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}