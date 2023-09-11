package duke;

import java.io.IOException;
import java.time.DateTimeException;

import duke.Command.Command;
import duke.Exception.DukeException;


/**
 * The Duke program is a chatbot named Beep Boop Bot that
 * executes commands to create and edit a tasklist.
 *
 * @author Inez Kok
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor for a Duke.
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.ui = new Ui();
        try {
            tasks = storage.saveTasks();
        } catch (IOException e) {
            tasks = new TaskList();
            e.printStackTrace();
        }
    }

    /**
     * Returns the String representation of the Duke response to a given input.
     *
     * @param input The user input given.
     * @return The String representation of the Duke response.
     */
    public String getResponse(String input) {
        try {
            ui.resetOutput();
            Command c = Parser.addToList(input, storage, tasks);
            c.execute(ui, storage, tasks);
            return ui.getOutput();
        } catch (DukeException e) {
            return ui.showError(e);
        } catch (IOException i) {
            return Parser.handleException(i);
        } catch (DateTimeException | NumberFormatException e) {
            return Parser.handleException(e);
        }
    }
}
