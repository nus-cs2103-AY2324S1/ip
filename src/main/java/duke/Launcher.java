package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        UiGUI uiGUI;
        Application.launch(Duke.class, args);
    }
}