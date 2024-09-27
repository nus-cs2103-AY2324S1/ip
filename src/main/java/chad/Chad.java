package chad;

import chad.command.Command;
import chad.exception.CommandException;
import chad.exception.LoadException;
import chad.util.Parser;
import chad.util.Storage;
import chad.util.TaskList;
import chad.util.Ui;

/**
 * Represents a Task Management Chat Bot.
 */
public class Chad {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Chad() {
        this.ui = new Ui();
        try {
            this.storage = new Storage("data/duke.txt");
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
