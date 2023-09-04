package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a AddCommand class that deals with the command to add a task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task to the list.
     *
     * @param taskList list of tasks.
     * @param ui       user interface.
     * @param storage  storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showAddTaskMessage(task, taskList.getSize());
        storage.saveListToDisk(taskList.getTasks());
    }
}
