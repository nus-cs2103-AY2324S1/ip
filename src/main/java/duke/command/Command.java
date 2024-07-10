package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * The base class for all commands that can be executed by Duke.
 */
public abstract class Command {

    /**
     * Checks if executing this command should cause the application to exit.
     *
     * @return If executing this command should exit the application.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command, carrying out the specific action defined by the subclass.
     *
     * @param tasks   The task list that the command may operate on.
     * @param ui      The user interface to interact with the user or display messages.
     * @param storage The storage handler to manage data persistence.
     * @throws DukeException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
