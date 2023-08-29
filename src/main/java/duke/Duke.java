package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class of the Duke application.
 * Initializes the user interface, storage, and task list, and runs the main loop of the application.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object with the given file path.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath The file path of the storage file.
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
     * Runs the main loop of the Duke application.
     * Reads user input, parses it, and executes the corresponding command.
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
    }

    /**
     * The main method of the Duke application.
     * Creates a Duke object and runs the main loop of the application.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/duke.txt").run();
    }
}

