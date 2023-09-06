package puke.gui;

import java.io.IOException;

import javafx.application.Application;
import puke.Puke;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) throws IOException {
        Application.launch(Puke.class, args);
    }
}
