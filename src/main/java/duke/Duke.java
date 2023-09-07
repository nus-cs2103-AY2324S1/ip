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
     */
    public Duke() {
        ui = new Ui(BOT_NAME);
        storage = new Storage(FILE_PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            // Solution below inspired by Addressbook Level 2.
            // The application cannot be expected to recover from such an exception.
            throw new RuntimeException(ui.getLoadingErrorMessage());
        }
    }

    /**
     * Returns the chatbot response after executing
     * the user input command.
     *
     * @param input The input by the user.
     * @return The response by the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

}
