package duke;

import javafx.application.Application;

public class Launcher {
    /**
     * The main entry point for the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws DukeException If there is an issue with Duke's execution.
     */
    public static void main(String[] args)  {
        Application.launch(Main.class, args);
    }
}