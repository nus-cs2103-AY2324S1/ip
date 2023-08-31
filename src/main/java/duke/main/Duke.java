package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The main class that initializes and runs the Duke Chat Bot.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String DATA_FILE_PATH = "./data/duke.txt";

    /**
     * The main entry point of the Duke application.
     *
     * @param args Command-line arguments.
     * @throws DukeException If there is an error during the execution of Duke.
     */
    public static void main(String[] args) throws DukeException {
        new Duke(DATA_FILE_PATH).executeDuke();
    }

    /**
     * Constructs a Duke instance with the specified data file path.
     *
     * @param filePath The file path for loading and saving task data.
     * @throws DukeException If there is an error during the initialization of Duke.
     */
    private Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        ui = new Ui();

        try {
            taskList = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            taskList = new TaskList();
            throw new DukeException("An error occurred during file loading.");
        }
    }

    /**
     * Executes the main logic of the Duke application and user interaction.
     */
    private void executeDuke() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


}

