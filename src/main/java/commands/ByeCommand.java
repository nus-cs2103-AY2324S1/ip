package commands;

import data.Actions;
import ui.UI;

/**
 * Represents the command to terminate the chatbot
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand by outputting a exit message.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     */
    @Override
    public String executeCommand(UI ui, Actions actionList) {
        return " Finally, I can rest.";
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
