package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * A method to launch the application
     * @param args list of arguments inputted
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
