package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The abstract base class for all commands that can be executed in Duke.
 */
public abstract class Command {


    /**
     * Checks if executing this command should exit the Duke application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList The list of tasks managed by Duke.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage component for saving and loading tasks.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
