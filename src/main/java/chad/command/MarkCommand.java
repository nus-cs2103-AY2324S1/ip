package chad.command;

import chad.exception.SaveException;
import chad.task.Task;
import chad.util.Storage;
import chad.util.TaskList;
import chad.util.Ui;

/**
 * Represents a Mark command to be executed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for MarkCommand class.
     *
     * @param index The TaskList index of the Task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the list of commands to mark a Task from a TaskList.
     *
     * @param taskList The given TaskList with the Task to be marked.
     * @param ui The given Ui to show the status of the marking of the task.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.mark(this.index);
        ui.addMarkMessage();
        ui.addTaskMessage(task);
        ui.addTaskListSizeMessage(taskList);
        try {
            storage.save(taskList);
        } catch (SaveException e) {
            ui.addErrorMessage(e);
        }
    }
}
