package alice;

import alice.command.Command;
import alice.exception.DukeException;
import alice.parser.Parser;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a personal application that helps a person to keep track of various things.
 */
public class Alice {
    private static final String FILE_PATH = "alice.txt";
    private final Storage storage; // The storage used to store the list of tasks.
    private final Ui ui; // The user interface.
    private TaskList tasks; // The list of tasks.

    /**
     * Constructs an Alice object.
     */
    public Alice() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initializes the application and starts the interaction with the user.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Alice().run();
    }

    /**
     * Runs the application until the user issues the exit command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDividerLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
        ui.close();
    }

    /**
     * Gets the response of the application to the user input.
     *
     * @param input The user input.
     * @return The response of the application to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
