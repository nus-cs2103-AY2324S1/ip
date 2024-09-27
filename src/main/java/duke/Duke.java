package duke;

import duke.command.Command;
import duke.exception.ChatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Initialises logic of duke.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Constructor for the class Duke.
     * @param filePath The file path where chat data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadFile();
        } catch (ChatException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns appropriate response to user input.
     * @param input The prompt received from user.
     * @return The appropriate response to given prompt.
     */
    public String getResponse(String input) {
        assert input != null : "invalid input";
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    }

}

