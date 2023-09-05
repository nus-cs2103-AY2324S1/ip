package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class of the Duke application.
 * Initializes the user interface, storage, and task list,
 * and runs the main loop of the application.
 */
public class Duke {

    /** The storage for saving and loading the task list */
    private final Storage storage;

    /** The task list */
    private TaskList tasks;

    /** The user interface for interacting with the user */
    private final Ui ui;

    /** Flag indicating whether the application should exit */
    private boolean isExit;

    /**
     * Constructs a Duke object with the given file path.
     * Initializes the user interface, storage, and task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./src/main/java/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Displays the welcome message for Duke.
     *
     * @return The welcome message as a string.
     */
    public String initialise() {
        ui.showWelcome();
        return ui.getCurrentStatus();
    }

    /**
     * Runs the main loop of the Duke application.
     * Reads user input, parses it, and executes the corresponding command.
     *
     * @param input The user's input text.
     */
    public void run(String input) {
        try {
            ui.showLine();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user's input text.
     * @return A response generated by Duke based on the user's input.
     */
    public String getResponse(String input) {
        run(input);
        return ui.getCurrentStatus();
    }
}

