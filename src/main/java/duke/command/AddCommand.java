package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to add task to a task list.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(task);
        ui.addTask(tasks, task);
    }
}
