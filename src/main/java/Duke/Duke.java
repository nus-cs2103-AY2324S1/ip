package duke;

import java.io.File;
import java.io.IOException;

import command.UserInterface;

/**
 * Duke is main class that controls the whole flow of the chatbot which makes of other classes like
 * Storage, TaskManager, UserInterface.
 */
public class Duke {

    private Storage storage;
    private TaskManager taskManager;
    private UserInterface userInterface;
    private Parser parser;

    /**
     * Constructor which instantiates a new Duke.
     *
     */
    public Duke() throws DukeException {
        //Default constructor
        userInterface = new UserInterface();

        File file = new File("data/duke.txt");
        if (!file.exists()) {
            try {
                File dataFolder = new File("data");
                dataFolder.mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("File cannot be created.");
            }
        }
        storage = new Storage(file);
        assert storage.load() != null : "Loading tasks from storage failed.";
        taskManager = new TaskManager(storage.load());
        parser = new Parser(taskManager, userInterface, storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        assert input != null && !input.isEmpty() : "User input should not be null or empty";
        try {
            String response = parser.parseCommand(input);
            return response;
        } catch (DukeException e) {
            return userInterface.showError(e.getMessage());
        }
    }
}


