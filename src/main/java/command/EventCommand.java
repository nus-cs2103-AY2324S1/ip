package command;

import java.time.LocalDate;

import enums.CommandWord;
import enums.ExceptionMessage;
import exceptions.WoofException;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.EventTask;
import tasks.TaskList;
import woof.Woof;

/**
 * The `EventCommand` class represents a command to create a new event task.
 * When executed, it parses the command, validates it, and adds a
 * new event task to the task list if the command is valid.
 */
public class EventCommand extends Command {

    /**
     * Constructs a new `EventCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public EventCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "event" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 6) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.EVENT.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.EVENT)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.EVENT.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[2]).equals(CommandWord.FROM)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_SUB_COMMAND_WORD.getValueFormat(
                    CommandWord.FROM.getValue()
                )
            );
        }

        try {
            Woof.validateDateTime(args[3]);
        } catch (WoofException e) {
            throw new WoofInvalidCommandException(e.getMessage());
        }

        if (!CommandWord.commandWordToValueMap(args[4]).equals(CommandWord.TO)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_SUB_COMMAND_WORD.getValueFormat(
                    CommandWord.TO.getValue()
                )
            );
        }

        try {
            Woof.validateDateTime(args[5]);
        } catch (WoofException e) {
            throw new WoofInvalidCommandException(e.getMessage());
        }
    }

    /**
     * Executes the "event" command. It parses the command, validates it, and adds a new
     * event task to the task list if the command is valid.
     *
     * @param taskList The task list to which the event task is added.
     */
    public String execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }
        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        LocalDate startDate = LocalDate.parse(args[3], super.getDateTimeFormatter());
        LocalDate endDate = LocalDate.parse(args[5], super.getDateTimeFormatter());
        return taskList.addTask(new EventTask(description, startDate, endDate));
    }

}
