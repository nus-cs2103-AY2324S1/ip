package command;

import enums.CommandWord;
import enums.ExceptionMessage;
import exceptions.WoofInvalidCommandException;
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
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length == 0) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.NULL_COMMAND.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.NULL_COMMAND)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.NULL_COMMAND.getValue()
                )
            );
        }
    }

    /**
     * Executes the "null" command. It validates the command and displays
     * a "confused" message to the user if the command is not recognized or valid.
     *
     * @param taskList The task list (not used in this command).
     */
    public String execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }
        return Ui.getConfusedMessage();
    }

}
