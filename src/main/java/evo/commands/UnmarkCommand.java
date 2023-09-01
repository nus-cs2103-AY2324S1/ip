package evo.commands;

import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;

/**
 * The UnmarkCommand class represents a command to mark a task as not done in a TaskList.
 * When executed, it sets the specified task as not completed and displays a confirmation message to the user.
 */

public class UnmarkCommand extends Command {
    /**
     * The task number (in string format) to be marked as not done.
     */
    protected String taskNumberToUnmark;

    /**
     * Constructs an UnmarkCommand with the specified task number to mark as not done.
     *
     * @param taskNumberToUnmark The task number (in string format) to be marked as not done.
     */
    public UnmarkCommand(String taskNumberToUnmark) {
        this.taskNumberToUnmark = taskNumberToUnmark;
    }

    /**
     * Executes the UnmarkCommand to mark a task as not done in the TaskList, updates the UI, and optionally the storage.
     *
     * @param tasksList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        // Mark a task as not done
        int taskNumberInList = Integer.parseInt(this.taskNumberToUnmark) - 1;
        tasksList.get(taskNumberInList).markAsNotDone();
        ui.showText("OK, I've marked this task as not done yet:");
        ui.showText("  " + tasksList.get(taskNumberInList).toString());
        ui.showNewLine();
    }
}
