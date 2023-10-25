package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
/**
 * Parent class of all the Command type.
 * Contains the abstract methods execute and isExit.
 */
public abstract class Command {
    /**
     * Executes the command from user input.
     *
     * @param tasks   List of tasks in taskList.
     * @param ui      Instance of the user interface.
     * @param storage Instance of the storage.
     * @return String representation of response to user input.
     * @throws DukeException If input is invalid.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
