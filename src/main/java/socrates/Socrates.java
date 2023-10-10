package socrates;

import socrates.command.Command;
import socrates.command.CommandResult;
import socrates.data.exception.SocratesException;
import socrates.data.exception.StorageLoadException;
import socrates.data.task.TaskList;
import socrates.parser.Parser;
import socrates.storage.Storage;

/**
 * Represents a chatbot. This initializes the chatbot application and is
 * the entry point to interaction with the user.
 */
public class Socrates {

    public static final String WELCOME_MESSAGE = "Hello! I'm SoCrates. What would you like to do today?";

    /** The file path where the data of the chatbot is stored */
    private static final String FILE_PATH = "data/tasks.txt";

    private final Storage storage;
    private final TaskList tasks;


    /**
     * Initializes the chatbot application with the given file path.
     * Loads up the data from the storage file, and shows the welcome message.
     *
     */
    public Socrates() {
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
    public CommandResult getResponse(String input) throws SocratesException {
        Command command = Parser.parse(input);
        return command.execute(tasks, storage);
    }

}
