package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

/**
 * Represents a command that can be executed by Duke.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage.
     * @return The response to the command.
     * @throws DukeException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns true iff the command is an exit command.
     *
     * @return True iff the command is an exit command.
     */
    public abstract boolean isExit();
}
