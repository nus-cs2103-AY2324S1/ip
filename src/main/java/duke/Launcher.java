package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Credit to: https://se-education.org/guides/tutorials/javaFx.html
 */
public class Launcher {
    public static void main(String[] args) {
        UiGUI uiGUI;
        Application.launch(Duke.class, args);
    }
}