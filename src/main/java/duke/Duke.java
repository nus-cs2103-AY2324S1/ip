package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a chatbot that helps users to keep track of their tasks.
 */
public class Duke {

    private static final String DEFAULT_FILE_NAME = "duke.txt";
    private static final String FOLD_PATH = "./data";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
    }

    /**
     * Initializes the Duke object.
     *
     * @return Welcome message.
     */
    public Response initialize() {
        this.storage = new Storage(FOLD_PATH, DEFAULT_FILE_NAME);
        this.ui = new Ui();
        try {
            taskList = new TaskList(storage.loadTasks(true, DEFAULT_FILE_NAME));
        } catch (DukeException e) {
            storage.createTaskFile();
            taskList = new TaskList();
            return ui.showLoadingError();
        }
        try {
            storage.loadAlias();
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
        return ui.showWelcome();
    }

    /**
     * Gets the response from Duke.
     *
     * @param input User input.
     * @return Response from Duke.
     */
    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
