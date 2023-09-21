package duke;

import duke.command.Command;
import duke.exception.CommandException;
import duke.exception.LoadException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Task Management Chat Bot.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (LoadException e) {
            ui.addErrorMessage(e);
        }
    }

    /**
     * Returns a response based on the given input.
     *
     * @param input The given user input from the GUI.
     * @return The String of the response from Chad.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
        } catch (CommandException e) {
            ui.addErrorMessage(e);
        } finally {
            output = ui.showMessage();
        }
        return output;
    }

}
