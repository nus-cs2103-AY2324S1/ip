package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a personal assistant chatbot that helps
 * to keep track of various tasks.
 */
public class Duke {
    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;

    /**
     * Constructs a Duke object.
     *
     * @param filePath The filepath for loading and saving the file.
     */
    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            UI.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves a welcome message for the user.
     *
     * @return A welcome message to greet the user.
     */
    public String getGreetings() {
        return UI.showWelcomeMessage();
    }

    /**
     * Generates a response to user input.
     * This method parses the user input, executes the corresponding command, and returns the response.
     *
     * @param input The user's input.
     * @return A response generated based on the user's input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, UI, STORAGE);
        } catch (DukeException e) {
            return UI.showErrorMessage(e.getMessage());
        }
    }
}




