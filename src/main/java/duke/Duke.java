package duke;

import duke.storage.Storage;
import duke.commands.Command;
import duke.parser.Parser;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The main class for the Duke application. Duke is a simple task manager.
 */
public class Duke {

    /** The storage object used to load and save task data. */
    private Storage storage;

    /** The task list object used to store and manipulate tasks. */
    private TaskList taskList;

    /** The UI object used to interact with the user. */
    private Ui ui;

    /**
     * Constructs a Duke instance with a specified file path for data storage.
     *
     * @param filePath The file path where task data is loaded from and saved to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printMessageWithSeparator("Error loading duke.tasks from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();
    }

    /**
     * Runs the Duke application, displaying a welcome message and processing user commands.
     */
    public void run() {
        this.ui.showWelcome();
        String userInput = ui.readCommand();
        Command command = null;
        while (true) {
            try {
                command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
            } catch (DukeException e) {
                ui.printMessageWithSeparator(e.getMessage());
            }

            if (command instanceof Command.Exit) {
                break;
            }

            userInput = ui.readCommand();
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
