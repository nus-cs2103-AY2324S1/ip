package crusader.command;

import crusader.TaskList;
import crusader.Ui;
import crusader.task.Task;

/**
 * Command used to add a task of any type to the bot.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.addTask(this.task);
        return String.format(
                "Adding the task:\n%s\nNow there are %d tasks in the list.",
                this.task.toString(),
                taskList.getSize());
    }
}
