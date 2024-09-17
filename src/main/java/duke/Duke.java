package duke;

import duke.commands.Command;
import duke.exceptions.JamesBondException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * The `Duke` class is the main class for the Duke task management application.
 * It handles the initialization of various components and the execution of the application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * Constructs a `Duke` object with the specified file path for data storage.
     *
     * @param filePath The file path where task data is stored.
     */
    public Duke(String filePath) {
        assert filePath != null : "File path cannot be null";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            TaskList tasks = new TaskList();
            tasks = storage.loadTasksFromFile();
            this.tasks = tasks;
        } catch (JamesBondException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Command c = Parser.parseCommand(input);
        return c.runCommand(tasks, ui, storage);
    }


}
