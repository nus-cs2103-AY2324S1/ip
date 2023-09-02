package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

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
        this.num = num;
    }

    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes a command to delete a task in the task list
     * @param tasksList Task list which contains the tasks
     * @param ui        A UI instance that displays a message to indicate to the user the task has been deleted
     * @param storage   Storage instance that represents the storage of the file
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        ui.showRemovedTask(tasksList, num);
        tasksList.removeTask(num);
    }
}
