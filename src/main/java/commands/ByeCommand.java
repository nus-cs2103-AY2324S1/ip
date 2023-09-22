package commands;

import data.Actions;
import ui.UI;

/**
 * Represents the "bye" command to terminate the chatbot
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand by sending a farewell message to the user.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) {
        ui.lineSandwich(" Finally, I can rest.");
    }

    /**
     * Ensures program exit.
     *
     * @return true to indicate program termination.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
