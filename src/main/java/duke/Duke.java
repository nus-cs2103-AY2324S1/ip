package duke;
import duke.utilities.Parser;
import duke.utilities.TaskList;
import duke.utilities.Storage;
import duke.utilities.Ui;
import duke.utilities.Input;

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

    public void run() {
        ui.greetings();
        boolean endSession = true;
        while(endSession) {
            String userInput = ui.inputSession();
            Input parsedInput = parser.parse(userInput);
            endSession = ui.handleInput(tasks, parsedInput, parser);
            tasks.overwriteTasksData(storage);
        }
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}
