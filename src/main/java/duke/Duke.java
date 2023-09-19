package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Represents the main Duke application.
 */
public class Duke {

    private boolean isExit = false;
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Duke object.
     *
     * @param filePath The path to the file where the task data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command should not be null";
            String response = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                isExit = true;
            }
            return response;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return isExit;
    }

    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
}
