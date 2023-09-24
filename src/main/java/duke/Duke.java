package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 *  The main class that represents the Duke but BTR chatbot application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructs a Duke object with the given file path.
     *
     * @param filePath The path to the file for storing task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load().getList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * Returns a response based on the given user input.
     *
     * @param input The String given by the user.
     * @return Returns a String response based on the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.handleInput(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
