package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;
import duke.exceptions.DukeException;

/**
 * Encapsulates the Command to run by the user input.
 */
public abstract class Command {
    /**
     * Executes the command, updates Tasks, Storage and prints to standard output and user feedback.
     *
     * @param tasks the Tasks object to be updated
     * @param ui Ui instance to print to standard output any user feedback
     * @param storage Storage instance to save into persistent storage
     * @param isRestoring boolean flag to track if the Command is executing in a restoring state
     * @throws DukeException if the Command cannot be executed due to invalid user input
     */
    abstract public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) throws DukeException;

    /**
     * Checks if the program should exit after the execution.
     *
     * @return a boolean value if the program should exit
     */
    public boolean isExit() {
        return false;
    }
}
