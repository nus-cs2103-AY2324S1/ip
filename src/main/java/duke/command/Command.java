package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command issued by the user and to be executed.
 */
public class Command {
    protected boolean isExit = false; // Whether the command is an exit command.

    /**
     * Executes the command for the given list of tasks, user interface and storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @throws DukeException If an error occurs during execution.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! I don't know what that means :-(");
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
