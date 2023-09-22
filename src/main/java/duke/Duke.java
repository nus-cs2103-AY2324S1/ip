package duke;


import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the main class of the Duke application, which manages user interactions and task management.
 * This class initializes the user interface, task list, storage, and parser for Duke.
 */
public class Duke {

    private static final String filePath = "./data/gideon.txt";
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;
    private Parser parser;


    /**
     * Initializes a Duke instance with the specified file path for task storage.
     * This constructor creates instances of the user interface, task list, storage, and parser.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks(), ui);
        this.parser = new Parser(ui, taskList);
    }


    /**
     * Processes user input and returns a corresponding command.
     *
     * @param input The user's input string.
     * @return A command to be executed.
     * @throws DukeException If an error occurs while processing the input.
     */
    public Command getResponse(String input) throws DukeException {
        return parser.parseCommand(input);
    }

    /**
     * Gets the task list managed by Duke.
     *
     * @return The task list.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Gets the storage used by Duke for task data.
     *
     * @return The storage object.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Gets the user interface used by Duke.
     *
     * @return The user interface object.
     */
    public Ui getUi() {
        return this.ui;
    }

}
