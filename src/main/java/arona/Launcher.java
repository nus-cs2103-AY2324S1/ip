package arona;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues and start the Arona application.
 */
public class Launcher {

    /**
     * The main method to launch the Arona application.
     *
     * @param args The command-line arguments (if any).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
