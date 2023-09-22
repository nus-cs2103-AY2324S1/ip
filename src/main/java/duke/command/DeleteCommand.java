package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Initializes a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list and returns a message.
     *
     * @param taskList The task list containing tasks.
     * @param storage  The storage component for saving tasks.
     * @param ui       The user interface for displaying messages.
     * @return A message indicating the deleted task and the updated number of tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.saveTasks(taskList);
        return ui.deleteTaskMessage(task, taskList.numOfTasks());
    }
}

