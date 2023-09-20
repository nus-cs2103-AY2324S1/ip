package duke;

import java.io.IOException;
import java.util.Arrays;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.DukeStorageException;
import duke.storage.Storage;
import javafx.application.Platform;

import static duke.common.Messages.MESSAGE_FIRST_PROMPT;

/**
 * The main class for the Duke chatbot.
 */
public class Duke {
    private final Parser parser;
    private boolean isAwaitingDataFilePath;
    private Storage storage;
    private TaskList tasks;

    /**
     * The constructor for the Duke application.
     * Sets up the relevant components required.
     */
    public Duke() {
        parser = new Parser();
        isAwaitingDataFilePath = true;
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
        if (isAwaitingDataFilePath) {
            isAwaitingDataFilePath = false;
            if (input.isEmpty()) {
                input = Storage.DEFAULT_STORAGE_PATH;
            }
            storage = new Storage(input);
            try {
                int tasksLoaded = loadTasks();
                if (tasksLoaded == 0) {
                    return new String[]{
                            String.format("No stored tasks found from %s", input),
                            "Starting from an empty task list.",
                            MESSAGE_FIRST_PROMPT
                    };
                } else {
                    return new String[]{
                            String.format("Tasks loaded from %s", input),
                            MESSAGE_FIRST_PROMPT
                    };
                }
            } catch (DukeStorageException e) {
                return new String[]{
                        String.format("Error loading tasks from %s", input),
                        "Starting from an empty task list.",
                        MESSAGE_FIRST_PROMPT
                };
            }
        }

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
