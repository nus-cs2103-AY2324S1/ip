package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.exception.DukeException;

/**
 * Represents an abstract command that can be executed by the user.
 */
public abstract class Command {

    /**
     * Executes the command, performing the specific actions associated with it.
     *
     * @param tasks   The list of tasks that may be manipulated by the command.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the execution of this command should trigger the program to exit.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
