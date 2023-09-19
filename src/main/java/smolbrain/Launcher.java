package smolbrain;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Creates a launcher.
     */
    public Launcher() {
    }

    /**
     * Starts the program.
     *
     * @param args String arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
