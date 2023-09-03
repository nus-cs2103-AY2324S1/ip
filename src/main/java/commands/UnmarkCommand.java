package commands;

import exceptions.JamesBondException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
/**
 * The `UnmarkCommand` class represents a command to unmark a specific task as done in the task list.
 * When executed, it unmarks the specified task and displays a message to the user.
 */
public class UnmarkCommand extends Command {
    /**
     * The task number to be unmarked.
     */
    protected int taskNumber;

    /**
     * Constructs an `UnmarkCommand` object with the task number to be unmarked.
     *
     * @param taskNumber The task number to identify the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the `UnmarkCommand` by unmarking the specified task and displaying a message to the user.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface responsible for displaying messages.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber - 1);
        task.unMark();
        ui.unmarkMessage(task);
    }
}

