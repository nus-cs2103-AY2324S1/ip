package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.DukeStorageException;
import duke.storage.Storage;
import javafx.application.Platform;

import java.io.IOException;
import java.util.Arrays;

/**
 * The main class for the Duke chatbot.
 */
public class Duke {
    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;

    /**
     * The constructor for the Duke application.
     * Sets up the relevant components required.
     */
    public Duke() {
        storage = new Storage();
        parser = new Parser();
    }

    /**
     * Loads the tasks from storage.
     *
     * @return the number of tasks loaded from storage
     * @throws DukeStorageException if an error occurs when loading tasks from storage
     */
    public int loadTasks() throws DukeStorageException {
        try {
            tasks = storage.load();
            return tasks.size();
        } catch (IOException e) {
            tasks = new TaskList();
            throw new DukeStorageException("Error reading from " + Storage.DEFAULT_STORAGE_PATH);
        }
    }

    /**
     * Returns Duke's response given the user input.
     *
     * @param input the input from the user
     * @return the response from Duke
     */
    public String[] getResponse(String input) {
        Command command = parser.parseCommand(input);
        command.setData(tasks);
        String[] response = command.execute();

        if (command.isBye()) {
            Platform.exit();
        }

        try {
            storage.save(tasks);
            return response;
        } catch (IOException e) {
            String[] newResponse = Arrays.copyOf(response, response.length + 1);
            newResponse[newResponse.length - 1] = "Error saving tasks";
            return newResponse;
        }
    }
}
