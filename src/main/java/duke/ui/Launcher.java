package duke.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Duke chatbot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
