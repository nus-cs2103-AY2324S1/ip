package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;

/**
 * Command to add a task into the task list.
 */
public class TaskCommand extends Command {
    private final Task task;

    public TaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(this.task);
        String output = "Got it. I've added this task:\n"
                + "    " + task.toString()
                + "\nNow you have " + taskList.size()
                + " tasks in the list.";
        return output;
    }
}
