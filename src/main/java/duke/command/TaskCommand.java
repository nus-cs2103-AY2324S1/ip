package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command for adding a task to the task list.
 */
public class TaskCommand extends Command {
    private Task task;

    /**
     * Initializes a TaskCommand with the task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public TaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the specified task to the task list.
     *
     * @param taskList The task list where the task will be added.
     * @param storage  The storage component for saving tasks after adding (not used in this command).
     * @param ui       The user interface for displaying messages.
     * @return A message indicating that the task has been added to the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return ui.addTaskMessage(task, taskList.numOfTasks());
    }
}

