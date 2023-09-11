package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The AddCommand class represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {
    private final Task TASK;

    /**
     * Constructs an AddCommand object.
     *
     * @param task Task to be added into the task list.
     */
    public AddCommand(Task task) {
        TASK = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(TASK);
        String message = ui.showAddedTask(TASK, taskList.getNumberOfTasks());
        storage.save(taskList.getList(), ui);
        return message;
    }
}
