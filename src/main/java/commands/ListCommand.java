package commands;

import data.Actions;
import duke.DukeException;
import ui.UI;

/**
 * Represents the command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying all tasks in the task list to the user.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks to be displayed.
     * @throws DukeException If any issues arise during the display process.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        ui.lineSandwich(actionList.stringList());
    }
}
