package rocket;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launch the application
     * @param args The arguments required to launch the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
