package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import ui.Ui;

/**
 * The `ByeCommand` class represents a command to exit the application.
 * When executed, it displays a bye message to the user.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new `ByeCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public ByeCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "bye" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 1) {
            return "Invalid number of arguments for the 'bye' command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.BYE)) {
            return "Invalid command word for the 'bye' command.";
        }

        return ""; // Return an empty string if the command is valid
    }


    /**
     * Executes the "bye" command. It shows the bye message to the user.
     *
     * @param taskList The task list (not used in this command).
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String validationError = validate(super.getRawCommand());
        if (isValidationError(validationError)) {
            return validationError;
        }
        return Ui.getByeUserMessage();
    }
}
