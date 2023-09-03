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
    /** Represents the filepath, storage, task list and UI of the bot. */
    private static final String FILEPATH = "./data/duke.txt";
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor method.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
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

    /**
     * Gets a String response to be displayed on the GUI.
     * @param input User input.
     * @return String response.
     * @throws DukeException if any error occurs.
     */
    public String getResponseToGui(String input) throws DukeException {
        Command c = Parser.parse(input);
        return c.executeGui(taskList, ui, storage);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
