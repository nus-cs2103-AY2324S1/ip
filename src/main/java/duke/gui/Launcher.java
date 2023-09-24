package duke.gui;

import duke.DukeException;
import javafx.application.Application;
/**
 * A launcher that starts the duke program.
 */
public class Launcher {
    /**
     * The main entry point for the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws DukeException If there is an issue with Duke's execution.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
