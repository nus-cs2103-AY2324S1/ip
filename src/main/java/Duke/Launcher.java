package Duke;


import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main method to launch the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

