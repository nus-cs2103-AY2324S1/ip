package commands;

import data.Actions;
import duke.DukeException;
import ui.UI;

/**
 * Represents a general command input.
 */
public abstract class Command {

    /**
     * Executes command-specific logic based on the subclass implementation.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException To handle errors during the command execution.
     */
    public abstract String executeCommand(UI ui, Actions actionList) throws DukeException;

    /**
     * Specifies if the command leads to program exit.
     *
     * @return false by default, unless overridden by subclass.
     */
    public boolean isExit() {
        return false;
    }
}
