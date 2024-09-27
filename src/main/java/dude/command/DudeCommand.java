package dude.command;

import dude.Storage;
import dude.exception.DudeException;
import dude.task.TaskList;

/**
 * Command for Dude.
 */
public abstract class DudeCommand {
    /**
     * Executes command and returns string response.
     *
     * @param taskList TaskList to operate on.
     * @param storage  Storage to operate on.
     * @return Response to command.
     * @throws DudeException If command execution fails.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DudeException;

    /**
     * Returns true if program should exit after command.
     *
     * @return True if program should exit after command is executed; false otherwise.
     */
    public abstract boolean isExit();
}
