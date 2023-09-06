package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Represents the main Duke application.
 */
public class Duke {

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
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c != null) {
                return c.execute(tasks, ui, storage);
            } else {
                return ui.showInvalidCommand();
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
