package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command for handling invalid user input.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the command to display an error message for invalid user input.
     *
     * @param taskList The task list containing tasks.
     * @param storage  The storage component for saving tasks.
     * @param ui       The user interface for displaying messages.
     * @return An error message indicating that the user input is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.invalidTaskMessage();
    }
}

