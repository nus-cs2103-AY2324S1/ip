package duke;

import javafx.application.Application;

/**
 * The Launcher class serves as the entry point for the Duke application.
 * It uses JavaFX's Application.launch method to launch the application by
 * starting the Main class.
 * This class is primarily used to work around classpath issues that can
 * occur when launching JavaFX applications.
 */
public class Launcher {

    /**
     * Launches the Duke application by invoking JavaFX's Application.launch
     * method with the Main class and any command-line arguments.
     * The main method of the Launcher class.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
