package command;

import enums.CommandWord;
import enums.ExceptionMessage;
import exceptions.WoofInvalidCommandException;
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
     */
    public static void validate(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";
  
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 1) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.BYE.getValue()
                )
            );
        }


        if (args[0] == null) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.NULL_ARGUMENT.getValueFormat(
                    CommandWord.BYE.getValue()
                )
            );
        }

        if (args[0].isEmpty()) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.EMPTY_ARGUMENT.getValueFormat(
                    CommandWord.BYE.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.BYE)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.BYE.getValue()
                )
            );
        }
    }


    /**
     * Executes the "bye" command. It shows the bye message to the user.
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

        return Ui.getByeUserMessage();
    }
}
