package koko;

import javafx.application.Application;

/**
 * Represents a launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the Duke chatbot application.
     *
     * @param args The command line arguments (unused)
     */
    public static void main(String[] args) {
        Application.launch(DukeGuiWrapper.class, args);
    }
}
