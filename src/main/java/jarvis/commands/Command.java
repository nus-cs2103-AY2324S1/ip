package jarvis.commands;

import jarvis.exceptions.DukeException;
import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

/**
 * Represents the Abstract Command Class.
 *
 * @author Shishir
 */
public abstract class Command {
    /**
     * Executes the required command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     * @throws DukeException Throws DukeException on invalid input.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns the exit status of the command.
     * @return Exit status of the command.
     */
    public abstract boolean isExit();
}
