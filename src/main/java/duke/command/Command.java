package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The Command class represents a command that can be executed in the Duke application.
 *
 * @author selwyn
 */
public abstract class Command {
    /** Indicates whether the command triggers an exit from the application. */
    private boolean isExit = false;

    /**
     * Executes the command using the provided TaskList, Ui, and Storage objects.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui The Ui object handling user interface interactions.
     * @param storage The Storage object handling storage-related operations.
     * @throws DukeException If there is an issue executing the duke.command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Toggles the exit status of the duke.command.
     */
    public void changeExitStatus() {
        this.isExit = !this.isExit;
    }

    /**
     * Checks whether the command triggers an exit from the application.
     *
     * @return True if the command triggers an exit, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}