package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The interface representing a command that can be executed on the task list.
 */
public interface Command {

    /**
     * Executes the command on the given task list, user interface, and storage.
     *
     * @param tasks   The task list on which the command will be executed.
     * @param ui      The user interface to display messages to the user.
     * @param storage The storage to update as needed by the command.
     */
    void execute(TaskList tasks, Ui ui, Storage storage);
}
