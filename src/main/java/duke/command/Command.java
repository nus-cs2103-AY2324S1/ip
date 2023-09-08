package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.DukeList;

/**
 * The Command class represents a generic command that can be executed.
 *
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Executes the command by performing specific actions on the task list and storage.
     *
     * @param dukeList The task list to which the command is applied.
     * @param storage  The storage object used for saving or retrieving data.
     * @return A string representing the result of executing the command.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract String execute(DukeList dukeList, Storage storage) throws DukeException;

    /**
     * Toggles the exit status of the command.
     * If set to true, it indicates that the command should exit the application.
     */
    public void setExitStatus() {
        this.isExit = !this.isExit;
    }

    /**
     * Checks if the command should exit the application.
     *
     * @return true if the command should exit, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }
}