package jeoe;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * The main method of Launcher.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // this launch method will find the start method and run it
        Application.launch(Main.class, args);
    }
}
