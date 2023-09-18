package sillybot;

import java.io.IOException;

import sillybot.commands.Command;
import sillybot.exceptions.IncompleteInputException;
import sillybot.tasks.TaskList;

/**
 * Represents a SillyBot object that runs the sillybot program.
 */
public class SillyBot {
    private TaskList tasks;
    private final Ui ui;
    private Storage storage;

    /**
     * Constructor for SillyBot.
     * Creates a new SillyBot object with the given file path.
     * Loads the tasks from the file path.
     * If the file path does not exist, creates a new file.
     *
     * @param filePath The path to the file where the tasks are stored.
     */
    public SillyBot(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IncompleteInputException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the response from SillyBot to display to the user.
     *
     * @param userInput The user input.
     * @return botResponse The response from SillyBot.
     */
    public String getResponse(String userInput) {
        String botResponse;

        if (tasks == null) {
            tasks = new TaskList();
        }

        try {
            Command command = Parser.parse(userInput);
            botResponse = command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return e.getMessage();
        }

        return botResponse;
    }
}
