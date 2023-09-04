package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements classes define specific actions for each command.
 */
public interface Command {

    /**
     * Executes the command with the specified task list, user interface, and storage.
     *
     * @param tasks   The list of tasks (to be modified or queried).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @throws DukeException If an error occurs during command execution.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Loads task-related data for the command.
     *
     * @param tasks The list of tasks to which task data can be added.
     */
    void loadTask(TaskList tasks);
}
