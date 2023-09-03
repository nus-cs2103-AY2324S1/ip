package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command given by the user.
 */
public abstract class Command {

    /**
     * Returns true if the command given by the user is to
     * exit the application, returns false otherwise.
     * @return
     */
    public abstract boolean isExit();

    /**
     * Executes the command with the given tasks, and prints the necessary
     * results to the ui. If the list of tasks is modified, the storage file
     * is saved again.
     * @param tasks The list of tasks in the application.
     * @param ui The text ui of the application.
     * @param storage The storage file of the applicaiton.
     * @throws DukeException If there is any issue with the constructed command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
