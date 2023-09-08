package command;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as undone.
 * This class extends the Command class and contains method to execute the unmark command
 * and to check whether it is an exit command.
 */
public class UnmarkCommand extends Command {

    // The number of the task to be mark as undone from the task list.
    int taskNumber;

    /**
     * Creates a new unmark command with the specified number for the task to be marked undone.
     *
     * @param taskNumber The number of the task to be marked as undone from the task list.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark command.
     * This method retrieves the task to be marked undone using the number, marks the task from the task list as undonoe,
     * and invokes UI to display the unmark task message.
     *
     * @param tasks The list of tasks on which the command will operate on.
     * @param ui The UI which is used during the command execution to show the unmark task text.
     * @param storage The storage where tasks are stored and retrieved from, currently not utilized in this method but can be extended to use in the future.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.taskNumber);
        task.unmarkDone();
        ui.showUnmarkText(task);
    }

    /**
     * Specifies that this command is not an exit command.
     *
     * @return false, as this command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
