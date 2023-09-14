package pogo;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        assert false : "This line should never be reached.";
        Application.launch(Pogo.class, args);
    }
}
