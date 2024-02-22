package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import graphicaluserinterface.MainWindow;

/**
 * The main class for the Duke application. Duke is a simple task manager.
 */
public class Duke {

    /** The storage object used to load and save task data. */
    private Storage storage;

    /** The task list object used to store and manipulate tasks. */
    private TaskList taskList;

    /** The GUI window used to interact with the user. */
    private MainWindow mainWindow;

    /**
     * Constructs a Duke instance with a default file path for data storage.
     */
    public Duke() {
        this("./data/duke.txt");
    }

    /**
     * Constructs a Duke instance with a specified file path for data storage.
     *
     * @param filePath The file path where task data is loaded from and saved to.
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            mainWindow.printMessage("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
     * Runs the Duke application, displaying a welcome message and processing user commands.
     */
    public void run(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            String response = command.execute(taskList, storage);
            this.mainWindow.setResponse(response);
        } catch (DukeException e) {
            this.mainWindow.setResponse(e.getMessage());
        }
    }

    /**
     * Sets the GUI window used to interact with the user.
     *
     * @param mainWindow The GUI window used to interact with the user.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Gets the task list object used to store and manipulate tasks.
     *
     * @return The task list object used to store and manipulate tasks.
     */
    public TaskList getTaskList() {
        return taskList;
    }
}
