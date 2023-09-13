package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;

/**
 * The `NullCommand` class represents a command that is not recognized or is not valid.
 * When executed, it shows a "confused" message to the user.
 */
public class NullCommand extends Command {

    /**
     * Constructs a new `NullCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public NullCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "null" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return "Invalid number of arguments for null command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.NULL_COMMAND)) {
            return "Invalid command word for null command.";
        }

        return ""; // Return an empty string if the command is valid
    }

    /**
     * Executes the "null" command. It validates the command and displays
     * a "confused" message to the user if the command is not recognized or valid.
     *
     * @param taskList The task list (not used in this command).
     */
    public String execute(TaskList taskList) {
        String validationError = validate(super.getRawCommand());
        if (isValidationError(validationError)) {
            return validationError;
        }
        return Ui.getConfusedMessage();
    }

}
