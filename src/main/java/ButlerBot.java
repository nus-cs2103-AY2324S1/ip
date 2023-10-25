import duke.command.Command;
import duke.components.DukeException;
import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

/**
 * Contains the main method to be executed.
 */
public class ButlerBot {
    /**
     * storage contains the instance of the storage.
     *
     * tasks contain the list of tasks.
     *
     * ui contains the instance of the user interface.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class. Instantiates the
     * storage with the filePath.
     *
     * @param filePath Contains the path to the save file.
     * @throws DukeException If filePath is invalid.
     */
    public ButlerBot(String filePath) throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Constructor for the Duke class.
     *
     * @throws DukeException
     */
    public ButlerBot() throws DukeException {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        this.tasks = new TaskList(storage.loadTasks());
        assert ui != null; // This ensures that the Ui instance is instantiated properly.
        assert storage != null; // This ensures that the Storage instance is instantiated properly.
        assert tasks != null; // This ensures that the TaskList instance is instantiated properly.
    }

    /**
     * Scans for user input and terminates on command "bye".
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.scan();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new ButlerBot("data/tasks.txt").run();
    }

    /**
     * Returns a response to the user's inputs.
     *
     * @param input
     * @return A String in response to user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
