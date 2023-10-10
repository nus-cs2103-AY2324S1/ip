package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command to list the tasks in the task list
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list the tasks in the task list
     * @param tasksList Task list which contains the tasks
     * @param ui A UI instance that displays the user the task list
     * @param storage Storage instance that saves the task to the task list
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        return ui.showList(tasksList);
    }
}
