package tasket;

import tasket.command.Command;
import tasket.command.CommandResult;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.parser.Parser;
import tasket.storage.Storage;
import tasket.ui.Ui;

/**
 * The class for Tasket.
 */
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
     * The constructor of Duke.
     * This is used by javafx.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
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

    public String greet() {
        return ui.showWelcome();
    }

    public void exit() {
        System.exit(0);
    }

    /**
     * Returns a response according to user input.
     * @param input The user input.
     * @return The response.
     */
    public CommandResult getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            String response = c.execute(taskList, ui, storage);
            return new CommandResult(response, c.isExit());
        } catch (TasketException e) {
            return new CommandResult(ui.showError(e.getMessage()), false);
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
