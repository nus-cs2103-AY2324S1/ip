package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, otherwise false.
     */
    public abstract boolean isExit();
}
