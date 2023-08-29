package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class of the bot.
 */
public class Duke {
    /** Represents the storage, task list and UI of the bot. */
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor method.
     *
     * @param filePath Filepath to an external file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.createList());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the bot.
     */
    public void run() {
        ui.helloGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
