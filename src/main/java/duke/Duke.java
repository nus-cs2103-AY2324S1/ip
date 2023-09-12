package duke;

import duke.command.Command;
import duke.data.exception.DukeException;
import duke.data.exception.StorageLoadException;
import duke.data.task.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 * Represents a chatbot. This initializes the chatbot application and is
 * the entry point to interaction with the user.
 */
public class Duke {

    /** The file path where the data of the chatbot is stored */
    private static final String FILE_PATH = "data/tasks.txt";


    private final Storage storage;
    private final TaskList tasks;


    /**
     * Initializes the chatbot application with the given file path.
     * Loads up the data from the storage file, and shows the welcome message.
     *
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageLoadException e) {
            // Solution below inspired by Addressbook Level 2.
            // The application cannot be expected to recover from such an exception.
            throw new RuntimeException(e.getMessage());
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
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
