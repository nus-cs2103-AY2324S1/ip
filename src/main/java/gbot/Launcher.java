package gbot;

import javafx.application.Application;
public class Launcher {
    /**
     * A launcher class to workaround classpath issues.
     *
     * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
