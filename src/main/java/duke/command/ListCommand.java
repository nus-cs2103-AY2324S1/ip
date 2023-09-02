package duke.command;

import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list on the user interface.
     *
     * @param tasks   The task list containing the tasks to be listed.
     * @param ui      The user interface.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(tasks.toString());
    }
}
