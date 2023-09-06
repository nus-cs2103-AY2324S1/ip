package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a personal application that helps a person to keep track of various things.
 */
public class Duke {
    private final Storage storage; // The storage used to store the list of tasks.
    private final Ui ui; // The user interface.
    private TaskList tasks; // The list of tasks.

    /**
     * Constructs a Duke object with the given file path.
     *
     * @param filePath The path of the file to store the list of tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
        new Duke("tasks.txt").run();
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
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }
}
