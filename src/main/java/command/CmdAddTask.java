package command;

import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to add a task to the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdAddTask extends Command {
    private Task task;

    /**
     * Constructs a CmdAddTask object with the task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public CmdAddTask(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     *
     * @param taskList The task list to which the task should be added.
     * @param ui       The user interface for displaying feedback to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(task);
        ui.print("Got it. I've added this task:");
        ui.print(task.toString());
        ui.print(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

}
