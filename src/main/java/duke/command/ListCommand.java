package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command List which lists the tasks in the task list.
 *
 * @author Armando Jovan Kusuma
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @return a list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listOfTasks(tasks, tasks.getTaskCount());
    }
}
