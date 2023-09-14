package tasket;

import tasket.command.Command;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.parser.Parser;
import tasket.storage.Storage;
import tasket.ui.Ui;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * The constructor of Duke.
     *
     * @param filePath The file path to save the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (TasketException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the duke program.
     * Shows welcome message, Gets the user input and executes commands until exits.
     * When there's error in the command, show error message.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseInput(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (TasketException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Creates duke object and run it.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
