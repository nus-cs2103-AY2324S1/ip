package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents an abstract class for executing commands in the Duke application.
 */
public abstract class Command {

    /**
     * Executes the command based on the provided task list, storage, and user interface.
     *
     * @param taskList The task list containing tasks.
     * @param storage  The storage component for saving tasks.
     * @param ui       The user interface for displaying messages.
     * @return A message or response from executing the command.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return " ";
    }
}
