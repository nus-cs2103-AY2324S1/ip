package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a placeholder command that does nothing.
 * This command is used when an unrecognized or empty user input is provided.
 */
public class EmptyCommand implements Command {
    /**
     * Executes the empty command, displaying an error message indicating
     * that the input is unrecognized or empty.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for saving task data.
     * @throws DukeException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.sendMessage("Error 404!!! Undone the previous command, but there is nothing to undo...");
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Does nothing
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) {
        return new EmptyCommand();
    }
}
