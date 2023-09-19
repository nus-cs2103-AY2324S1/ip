package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This class is a child of the abstract Command class
 * and contains method to execute the delete command,
 * including methods to check whether it is an exit command.
 */
public class DeleteCommand extends Command {

    /** Number of the task in the task list */
    private int taskNumber;

    /**
     * Creates a new delete command with the specified number for the task to be deleted.
     *
     * @param taskNumber The number of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command.
     * This method retrieves the task to be deleted using the number,
     * deletes the task from the task list,
     * and invokes UI to display the delete task message.
     *
     * @param tasks The list of tasks on which the command will operate on.
     * @param ui The UI which is used during the command execution to show delete text.
     * @param storage The storage where tasks are stored and retrieved from, currently
     *                not utilized in this method but can be extended to use in the future.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.taskNumber);
        assert task != null; // Check task is not null.
        tasks.delete(this.taskNumber);
        return ui.showDeleteText(task, tasks.size());
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
