package commands;

import data.Actions;
import ui.UI;

/**
 * Represents a command that is unrecognized or unsupported by the system.
 */
public class UnknownCommand extends Command {
    private final String input;

    /**
     * Constructs an UnknownCommand with the provided user input.
     *
     * @param input The user input that was not recognized by the system.
     */
    public UnknownCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command by displaying an error message to inform the user
     * that the entered command is unsupported.
     *
     * @param ui The UI instance through which the error message is displayed.
     * @param actionList The list of tasks. This parameter is not used in this context
     *                   but is included to maintain a consistent method signature with other commands.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) {
        ui.lineSandwich(" Well, looks like " + input + " is unsupported.");
    }
}
