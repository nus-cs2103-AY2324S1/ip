package arona.commands;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to unmark a task as done. When executed, this command
 * unmarks a specified task in the task list and updates its status in storage.
 */
public class UnMarkCommand extends Command {
    private Storage storage;
    private int taskIndex;

    /**
     * Initializes a new instance of the UnMarkCommand class with the specified
     * task list, user interface, storage, and task index.
     *
     * @param taskList   The task list containing the tasks.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage responsible for loading and saving tasks.
     * @param taskIndex  The index of the task to be unmarked in the task list.
     */
    public UnMarkCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
        super(taskList, ui);
        this.storage = storage;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by unmarking the specified task in the task list,
     * updating its status in storage, and displaying the result to the user.
     */
    @Override
    public void execute() {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            ui.showTaskDoesNotExist(taskIndex);
            return;
        }
        taskList.getTasks().get(taskIndex).unMark();
        storage.updateTaskStatusAsUnmarked(taskIndex);
        ui.showTaskUnmarked(taskList.getTasks().get(taskIndex));
    }
}
