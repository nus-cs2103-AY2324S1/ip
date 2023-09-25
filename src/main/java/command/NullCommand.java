package command;

import enums.CommandWord;
import enums.WoofMessage;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.TaskList;

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
     * Validates the `NullCommand`.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthNotEquals(CommandWord.NULL_COMMAND, args, 0);
        validateNotNullArgs(CommandWord.NULL_COMMAND, args);
        validateNotEmptyArgs(CommandWord.NULL_COMMAND, args);
        validateCommandWord(CommandWord.NULL_COMMAND, args[0]);
    }

    /**
     * Executes the `NullCommand`. It validates the command and displays
     * a "confused" message to the user if the command is not recognized or valid.
     *
     * @param taskList The task list (not used in this command).
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }
        return WoofMessage.CONFUSED.toFormattedValue(rawCommand);
    }

}
