package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command for the Duke application.
 *
 * @author Joseph Oliver Lim
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     * @throws DukeException If the execution of the command fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
