package peko;


import javafx.application.Application;

/**
 * The `Launcher` class is responsible for launching the Peko JavaFX application.
 * It includes the main method, which serves as the entry point for the application.
 */
public class Launcher {

    /**
     * The main method that launches the Peko JavaFX application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Peko.class, args);
        //Application.launch(Main.class, args);
    }
}
