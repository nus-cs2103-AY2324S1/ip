package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The AddCommand class represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand object.
     *
     * @param task Task to be added into the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.task);
        ui.showAddedTask(this.task, taskList.getNumberOfTasks());
        storage.save(taskList.getList(), ui);
    }
}
