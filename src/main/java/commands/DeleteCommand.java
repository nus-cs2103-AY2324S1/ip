package commands;

import exceptions.JamesBondException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The DeleteCommand class represents a command to delete a specific task from the task list.
 * It takes the task number as an argument to identify the task to be deleted.
 */
public class DeleteCommand extends Command {

    /**
     * The task number to be deleted.
     */
    int taskNumber;

    /**
     * Constructs a DeleteCommand object with the task number to be deleted.
     *
     * @param taskNumber The task number to identify the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand by deleting the specified task from the task list.
     * It then displays a message to inform the user about the deletion.
     *
     * @param taskList The task list from which the task is to be deleted.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage component for reading or writing data.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(taskNumber - 1);
        taskList.delete(taskNumber - 1);
        int len = taskList.len();
        ui.showDeleteMessage(task, len);
    }
}
