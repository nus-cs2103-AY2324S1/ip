package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.task.Task;

/**
 * Command for adding a task to the task list.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the stored task into the given task list.
     * Displays the resultant task list after addition.
     * @param storage Given storage.
     * @param taskList Given task list.
     * @return  Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DookException {

        return taskList.addTask(task);
    }
}
