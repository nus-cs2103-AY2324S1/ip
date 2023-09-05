/**
 * Represents the main class of the Duke application, which manages user interactions and task management.
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Initializes a Duke instance with the specified file path for task storage.
     *
     * @param filePath The file path for task storage.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.loadTasks(), ui);
        this.parser = new Parser(ui, taskList);
    }

    /**
     * Starts the Duke application, displaying a welcome message, processing user commands, and saving tasks.
     */
    public void run() {
        ui.welcomeMessage();
        String command = ui.getUserInput();
        while (parser.parseCommand(command)) {
            command = ui.getUserInput();
            storage.saveTasks(taskList);
        }
        ui.exit();
    }

    /**
     * The entry point for the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("./data/gideon.txt").run();
    }
}
