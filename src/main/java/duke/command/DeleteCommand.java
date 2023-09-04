package duke.command;

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84
/**
 * Represents a command to delete a task from the task list.
 * The DeleteCommand is responsible for removing a task from the task list based on its index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand.
     * This method attempts to delete the task at the specified index from the task list.
     * If the index is valid, the task is deleted, and the task list and storage are updated.
     * If the index is invalid, an error message is displayed.
     *
     * @param taskList The TaskList object that stores the list of tasks.
     * @param ui       The Ui object responsible for user interface interactions.
     * @param storage  The Storage object responsible for reading and writing data to a file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskToDelete = taskList.getTask(index);
            taskList.deleteTask(taskToDelete);
            ui.displayDeleteTask(taskToDelete, taskList.numTasks());
            storage.saveTasks(taskList.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMessage("Invalid task index.");
        }
    }
}