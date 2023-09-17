package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
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
    public String initialize() {
        this.storage = new Storage(FOLD_PATH, DEFAULT_FILE_NAME);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.loadTasks(true, DEFAULT_FILE_NAME));
            return ui.showWelcome();
        } catch (DukeException e) {
            storage.createTaskFile();
            taskList = new TaskList();
            return ui.showLoadingError();
        }
    }

    /**
     * Gets the response from Duke.
     *
     * @param input User input.
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(taskList, ui, storage);
            if (c.isExit()) {
                System.exit(0);
            }
            return response;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
