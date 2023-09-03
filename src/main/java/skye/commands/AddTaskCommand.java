package skye.commands;

import skye.data.TaskList;
import skye.data.task.Task;
import skye.storage.Storage;
import skye.ui.UI;

import java.io.IOException;

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
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.write(taskList.getTasks());
        ui.showAddedTask(task, taskList.getTasks());
    }
}