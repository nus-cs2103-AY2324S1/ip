package commands;

import data.Actions;
import duke.DukeException;
import tasks.Task;
import ui.UI;

/**
 * Represents the "delete" command which deletes the specified task.
 *
 */
public class DeleteCommand extends Command {
    private int deleteTaskNumber;

    /**
     * Initializes a DeleteCommand with the task number of the task to be deleted.
     *
     * @param deleteTaskNumber The task number of the task to be deleted.
     */
    public DeleteCommand(int deleteTaskNumber) {
        this.deleteTaskNumber = deleteTaskNumber;
    }

    /**
     * Executes the DeleteCommand by deleting the specified task from the task list.
     * If successful, it will confirm the deletion to the user through the UI.
     * Otherwise, it will throw an exception.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException If the specified task does not exist in the task list.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task deletedTask = actionList.delete(deleteTaskNumber);
        if (deletedTask != null) {
            ui.lineSandwich(" Noted. I've removed this task:\n  " + deletedTask + "\n Now you have "
                    + actionList.size() + " tasks in the list.");
        } else {
            throw new DukeException(" tasks.Task does not exist, please review input");
        }
    }
}
