package dre;

import javafx.application.Application;

/**
 * Launcher class to initialize the Dre application.
 * Works around classpath issues.
 */
public class Launcher {
    /**
     * The main entry point for the Dre application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String... args) {
        Application.launch(Main.class, args);
    }
}