package arona.commands;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to mark a task as done. When executed, this command marks
 * the specified task as done, updates its status in storage, and displays a
 * confirmation message.
 */
public class MarkCommand extends Command {
    private Storage storage;
    private int taskIndex;

    /**
     * Initializes a new instance of the MarkCommand class with the specified
     * task list, user interface, storage, and task index.
     *
     * @param taskList   The task list containing the tasks.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage responsible for loading and saving tasks.
     * @param taskIndex  The index of the task to be marked as done.
     */
    public MarkCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
        super(taskList, ui);
        this.storage = storage;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "Mark" command by marking the specified task as done, updating
     * its status in storage, and displaying a confirmation message to the user.
     * If the task index is out of bounds, an error message is shown.
     */
    @Override
    public void execute() {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            ui.showTaskDoesNotExist(taskIndex);
            return;
        }
        taskList.getTasks().get(taskIndex).mark();
        storage.updateTaskStatusAsMarked(taskIndex);
        ui.showTaskMarkedAsDone(taskList.getTasks().get(taskIndex));
    }
}
