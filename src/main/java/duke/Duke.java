package duke;

import duke.utils.Session;
import javafx.application.Application;

/**
 * The Duke class serves as the entry point to the Duke application.
 * It creates an instance of the Session class and starts the chatbot session.
 */
public class Duke {
    /**
     * The main method that launches the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Application.launch(Session.class, args);
    }
}
