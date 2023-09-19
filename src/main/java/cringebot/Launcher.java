package cringebot;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the application.
     *
     * @param args args from the CLI.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
