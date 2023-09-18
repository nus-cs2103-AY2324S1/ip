package command;

import enums.CommandWord;
import enums.WoofMessage;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.TaskList;

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
     * Validates the `ByeCommand`.
     * It checks if the command is correctly formatted and if the specified task index is valid.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthEquals(CommandWord.BYE, args, 1);
        validateNotNullArgs(CommandWord.BYE, args);
        validateNotEmptyArgs(CommandWord.BYE, args);
        validateCommandWord(CommandWord.BYE, args[0]);
    }

    /**
     * Executes the `ByeCommand`. It shows the bye message to the user.
     *
     * @param taskList The task list (not used in this command).
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        try {
            validate(super.getRawCommand());
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }

        return WoofMessage.BYE.toFormattedValue();
    }
}
