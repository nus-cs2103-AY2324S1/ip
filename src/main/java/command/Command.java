package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Stores information of the action to be executed.
 */
public abstract class Command {

    /**
     * Executes the command given by the user.
     *
     * @param tasks task.Task list storing user task.
     * @param ui The duke.Ui handling user interactions.
     * @param store The store that handles file operations.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    /**
     * Returns if Chat bot should continue after commands.
     *
     * @return Boolean value representing if Chat Bot should end.
     */
    public abstract boolean isExit();

}
