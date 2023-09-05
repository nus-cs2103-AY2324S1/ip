package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ListCommand class represents a command for listing all tasks in the task list.
 *
 * @author selwyn
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand by displaying all tasks in the task list.
     *
     * @param taskList The task list to be displayed.
     * @param storage  The storage object (not used in this command).
     * @return A message displaying all tasks in the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.printTaskList(taskList, false);
    }
}
