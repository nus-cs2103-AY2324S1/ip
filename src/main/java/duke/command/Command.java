package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command that can be executed by the Duke application.
 */
public abstract class Command {

    /**
     * Executes the command with the provided TaskList, Storage, and Ui objects.
     *
     * @param taskList The TaskList containing the task data.
     * @param storage  The Storage object responsible for saving and loading data.
     * @param ui       The Ui object responsible for user interface interactions.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);
}
