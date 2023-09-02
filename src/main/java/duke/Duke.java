package duke;

import duke.utilities.Input;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * The main class for Duke Chatbot
 */
public class Duke {

    /** Variable to store task list */
    private Storage storage;

    /** Variable to handle list of tasks operations */
    private TaskList tasks;

    /** Variable to handle user interactions */
    private Ui ui;

    /** Variable to handle user inputs */
    private Parser parser;

    /**
     * Creates a new instance of Duke chatbot
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        if (storage.fileExists()) {
            tasks = new TaskList(storage.loadTasksData());
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the execution of the chatbot
     */
    public void run() {
        ui.greet();
        boolean endSession = true;
        while (endSession) {
            String userInput = ui.startInputSession();
            Input parsedInput = parser.parse(userInput);
            endSession = ui.handleInput(tasks, parsedInput, parser);
            tasks.overwriteTasksData(storage);
        }
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}
