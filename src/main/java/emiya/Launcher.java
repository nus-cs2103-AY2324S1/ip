package emiya;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Constructor for this class.
     */
    public Launcher() {
    }

    /**
     * Used to start the program.
     *
     * @param args String arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
