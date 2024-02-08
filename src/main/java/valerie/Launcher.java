package valerie;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    // Entry point of the application, launches the Main class.
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
