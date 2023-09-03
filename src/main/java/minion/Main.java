package minion;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApplication.class, args);
    }
}
