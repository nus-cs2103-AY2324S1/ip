package duke;

import duke.command.Command;
import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a chatbot. This initializes the chatbot application and is
 * the entry point to interaction with the user.
 */
public class Duke {

    /** The name of the chatbot */
    private static final String BOT_NAME = "SoCrates";

    /** The file path where the data of the chatbot is stored */
    private static final String FILE_PATH = "data/tasks.txt";


    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;


    /**
     * Initializes the chatbot application with the given file path.
     * Loads up the data from the storage file, and shows the welcome message.
     *
     * @param filePath The file path where the data of the chatbot is stored.
     */
    public Duke(String filePath) {
        ui = new Ui(BOT_NAME);
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
            ui.showWelcomeMessage();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application.
     *
     * @param args The arguments specified by the user at program launch.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }

    /**
     * Repeatedly reads user command and executes it until
     * the user inputs the ExitCommand. If the given command
     * does not exist, prints an error message.
     */
    private void run() {
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException exception) {
                ui.showErrorMessage(exception.getMessage());
            }
        }
    }

}
