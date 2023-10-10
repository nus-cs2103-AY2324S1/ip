package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.data.TaskList;
import skye.data.task.Task;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents a generic command to handle adding of Task objects and its subclasses.
 */
public class AddTaskCommand extends Command {

    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the AddTaskCommand and its subclasses AddDeadlineCommand, AddEventCommand and AddToDoCommand.
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager) throws IOException {
        TaskList taskList = listManager.getTaskList();
        taskList.addTask(task);
        storageManager.writeTasks(taskList.getTasks());
        return ui.showAddedTask(task, taskList.getTasks());
    }
}
