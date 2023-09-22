package commands;

import data.Actions;
import duke.DukeException;
import tasks.Task;
import ui.UI;

/**
 * Represents the command to mark a specific task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Initializes a MarkCommand with the task number of the task to be marked as done.
     *
     * @param taskNumber The integer representing the position of the task in the list of tasks.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkCommand by marking the specified task as done. If the task is
     * successfully marked, a confirmation message is shown. If the task number does not exist,
     * an error message is displayed.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException If any issues arise during the marking process.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task resultingTask = actionList.mark(taskNumber);
        if (resultingTask != null) {
            ui.lineSandwich(" Fine. Marked this task as done, better not change your mind:\n " + resultingTask);
        } else {
            ui.lineSandwich(" Task number does not exist, review input.");
        }
    }
}
