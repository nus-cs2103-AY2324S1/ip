package main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        //FOR GUI
        Application.launch(main.Duke.class, args);
    }
}
