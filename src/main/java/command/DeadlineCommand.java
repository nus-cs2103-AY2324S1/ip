package command;

import java.time.LocalDate;

import enums.CommandWord;
import enums.ExceptionMessage;
import exceptions.WoofException;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.DeadlineTask;
import tasks.TaskList;
import woof.Woof;

/**
 * The `DeadlineCommand` class represents a command to create a new deadline task.
 * When executed, it parses the command and adds a new deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a new `DeadlineCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public DeadlineCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "deadline" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException is the command is invalid.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 4) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.DEADLINE.getValue()
                )
            );
        }

        if (args[0] == null || args[1] == null || args[2] == null || args[3] == null) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.NULL_ARGUMENT.getValueFormat(
                    CommandWord.DEADLINE.getValue()
                )
            );
        }

        if (args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.EMPTY_ARGUMENT.getValueFormat(
                    CommandWord.DEADLINE.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DEADLINE)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.DEADLINE.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.BY)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_SUB_COMMAND_WORD.getValueFormat(
                    CommandWord.BY.getValue()
                )
            );
        }

        try {
            Woof.validateDateTime(args[3]);
        } catch (WoofException e) {
            throw new WoofInvalidCommandException(e.getMessage());
        }
    }

    /**
     * Executes the "deadline" command. It parses the command, validates it, and adds a new
     * deadline task to the task list if the command is valid.
     *
     * @param taskList The task list to which the deadline task is added.
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }

        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        LocalDate endDate = LocalDate.parse(args[3], super.getDateTimeFormatter());
        return taskList.addTask(new DeadlineTask(description, endDate));
    }

}
