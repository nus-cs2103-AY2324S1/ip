package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Represents the main class of the Duke application.
 * Duke is a chatbot that helps manage tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath The path to the file used for storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            if (commandIsExit(c)) return ui.displayMessage();
        } catch (DukeException e) {
            return getExceptionString(e);
        }
        return input;
    }

    private String getExceptionString(DukeException e) {
        ui.sendMessage(e.getMessage());
        return ui.displayMessage();
    }

    private boolean commandIsExit(Command c) {
        if (c.isExit()) {
            Platform.exit();
        } else {
            return true;
        }
        return false;
    }
}
