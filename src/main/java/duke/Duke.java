package duke;


import duke.command.Command;

/**
 * Represents the main class of the Duke application, which manages user interactions and task management.
 */
public class Duke {

    private static final String filePath = "./data/gideon.txt";
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;
    private Parser parser;


    /**
     * Initializes a Duke instance with the specified file path for task storage.
     *
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks(), ui);
        this.parser = new Parser(ui, taskList);
    }


    /**
     * The entry point for the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    /*public static void main(String[] args) {
        new Duke().run();
    }*/

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public Command getResponse(String input) {
        return parser.parseCommand(input);
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }
}
