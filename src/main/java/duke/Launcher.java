package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main function of the Launcher class.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

