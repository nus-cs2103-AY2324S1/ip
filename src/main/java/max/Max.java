package max;

import java.time.format.DateTimeParseException;

import max.commands.Command;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.tasks.TaskList;
import max.ui.Ui;

/**
 * Initialises the Max application.
 */
public class Max {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Initialises Storage, TaskList and Ui.
     * Loads storage from specified file location.
     *
     * @param filePath file location of stored task list
     */
    public Max(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MaxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Public constructor for Max.
     * Initialises Storage, TaskList and Ui into specified file location.
     *
     */
    public Max() {
        this("./data/max.txt");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command c = parser.parse(input);
            isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (MaxException e) {
            return ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.showError("Please use yyyy-mm-dd format!");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Please use proper formats!");
        }
    }

    /**
     * Checks if the exit command was called.
     *
     * @return True if last command is exit command, false otherwise.
     */
    public boolean willExit() {
        return isExit;
    }
}
