package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command List that lists the tasks in the task list.
 *
 * @author Joseph Oliver Lim
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks, tasks.getCountTasks());
    }
}
