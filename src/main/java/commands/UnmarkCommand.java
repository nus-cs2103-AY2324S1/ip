package commands;

import data.Actions;
import duke.DukeException;
import tasks.Task;
import ui.UI;

/**
 * Represents the command to unmark a specific task.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Initializes a UnmarkCommand with the task number of the task to be unmarked.
     *
     * @param taskNumber The integer representing the position of the task in the list of tasks.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnmarkCommand by marking the specified task as done. If the task is
     * successfully unmarked, a confirmation message is shown. If the task number does not exist,
     * an error message is displayed.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException If any issues arise during the unmarking process.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task resultingTask = actionList.unmark(taskNumber);
        if (resultingTask != null) {
            ui.lineSandwich(" Well, you changed your mind :(. Just this once:\n " + resultingTask);
        } else {
            ui.lineSandwich(" Task number does not exist, review input.");
        }
    }
}
