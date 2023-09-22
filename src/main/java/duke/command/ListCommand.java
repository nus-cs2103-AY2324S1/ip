package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command for listing all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to display all tasks in the task list.
     *
     * @param taskList The task list containing tasks.
     * @param storage  The storage component for saving tasks (not used in this command).
     * @param ui       The user interface for displaying tasks.
     * @return A message displaying all tasks in the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.showTasks(taskList);
    }
}

