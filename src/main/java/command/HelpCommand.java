package command;

import enums.WoofMessage;
import exceptions.WoofInvalidCommandException;
import tasks.TaskList;

/**
 * The `HelpCommand` class represents a command request for help.
 * When executed, it displays a help message to the user.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new `HelpCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public HelpCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the `HelpCommand`.
     * It checks if the command is correctly formatted and if the specified task index is valid.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = parser.Parser.getArgs(rawCommand);
        validateArgsLengthEquals(enums.CommandWord.HELP, args, 1);
        validateNotNullArgs(enums.CommandWord.HELP, args);
        validateNotEmptyArgs(enums.CommandWord.HELP, args);
        validateCommandWord(enums.CommandWord.HELP, args[0]);
    }

    /**
     * Executes the `HelpCommand`. It shows the bye message to the user.
     */
    public String execute(TaskList taskList) {
        try {
            validate(super.getRawCommand());
        } catch (exceptions.WoofInvalidCommandException e) {
            return e.getMessage();
        }

        return WoofMessage.HELP.toFormattedValue();
    }
}
