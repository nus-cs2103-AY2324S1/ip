package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to handle invalid user input by displaying an error message.
 */
public class InvalidCommand implements Command {
    private String errorMessage;

    /**
     * Constructs an InvalidCommand with the specified error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
