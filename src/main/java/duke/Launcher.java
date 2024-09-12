package duke;

import javafx.application.Application;

/**
 * The Launcher class is responsible for starting the Duke chatbot application.
 * It initiates the application's main entry point, the 'Main' class.
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
