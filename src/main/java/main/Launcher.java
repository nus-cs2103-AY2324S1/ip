package main;
import javafx.application.Application;


/**
 * A launcher class to workaround classpath issues.
 *
 * @author Wong Joon Hung
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}