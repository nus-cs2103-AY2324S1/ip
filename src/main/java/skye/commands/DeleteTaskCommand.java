package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.data.TaskList;
import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the command for deleting tasks.
 */
public class DeleteTaskCommand extends DeleteCommand {

    public static final String RESOURCE = "tasks";

    /**
     * Instantiates the delete command for deleting tasks
     *
     * @param taskNumber Index number on the task list
     */
    public DeleteTaskCommand(int taskNumber) {
        super(taskNumber);
    }

    /**
     * Executes the delete task command
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     * @throws DukeException Describes the error encountered when executing the command
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager)
            throws DukeException, IOException {
        TaskList taskList = listManager.getTaskList();
        Task removedTask = taskList.deleteTask(getIndex());
        storageManager.writeTasks(taskList.getTasks());
        return ui.showRemovedTask(removedTask, taskList.getTasks());
    }
}
