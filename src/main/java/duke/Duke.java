package duke;
import duke.UI.UI;
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;

/**
 * The main class that manages the Duke application.
 * This class handles the initialization, execution, and termination of the application.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    /**
     * Constructs a Duke object with the specified file path.
     * Initializes the UI, storage, and tasks based on loaded data or an empty list.
     *
     * @param filePath The file path for storing and loading task data.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     * Displays a welcome message, reads and processes user input in a loop,
     * and handles exceptions and command execution.
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
                ui.showMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method that starts the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
