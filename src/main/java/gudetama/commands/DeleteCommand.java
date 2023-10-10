package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command to delete a task in the task list
 */
public class DeleteCommand extends Command {
    /**
     * Index of the task to be deleted in the task list
     */
    private final int num;

    /**
     * Constructor for DeleteCommand
     * @param num Index of the task to be deleted in the task list
     */
    public DeleteCommand(int num) {
        assert num > 0 : "Task index should be greater than 0.";
        this.num = num;
    }

    /**
     * Executes a command to delete a task in the task list
     * @param tasksList Task list which contains the tasks
     * @param ui        A UI instance that displays a message to indicate to the user the task has been deleted
     * @param storage   Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        String result = ui.showRemovedTask(tasksList, num);
        tasksList.removeTask(num);
        return result;
    }
}
