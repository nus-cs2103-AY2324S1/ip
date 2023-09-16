package duke.gui;

import javafx.application.Application;

/**
 * Represents a launcher class to workaround classpath issues.
 * Reused from this <a href="https://se-education.org/guides/tutorials/javaFx.html">tutorial</a>
 */
public class Launcher {
    /**
     * Launches the application.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
