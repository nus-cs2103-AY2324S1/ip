package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * R
 */
public class ListCommand extends Command {
    /**
     * Displays the list of tasks.
     *
     * @param taskList list of tasks
     * @param ui       user interface
     * @param storage  storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList);
    }
}
