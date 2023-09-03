package duke;
import java.io.IOException;

import duke.command.Command;
import duke.storage.Storage;
import duke.ui.Ui;



/**
 * The main class for the Duke program.
 * It manages the initialization, execution, and termination of the program.
 */
public class Duke {

    /** File path for task storage. */
    private static final String FILE_PATH = "./data/duke.txt";
    /** Handles reading and storing tasks. */
    private Storage storage;
    /** List of tasks managed by Duke. */
    private TaskList tasks;

    /** User interface for interactions. */
    private final Ui ui;
    /**
     * Constructs a new Duke instance with the specified file path for storage.
     *
     * @param filePath The path to the file for task storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException ie) {
            System.exit(0);
        } catch (DukeException de) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Executes the Duke program. It initializes the user interface,
     * awaits user commands, executes them, and exits when needed.
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
            } catch (IOException ie) {
                ui.showError(ie.getMessage());
            } catch (DukeException de) {
                ui.showError(de.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method for the Duke program. It initializes and runs the Duke program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        new Duke(FILE_PATH).run();
    }
}
