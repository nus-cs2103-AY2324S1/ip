package command;

import java.time.LocalDate;

import enums.CommandWord;
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
     * Validates the `EventCommand`.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthEquals(CommandWord.EVENT, args, 6);
        validateNotNullArgs(CommandWord.EVENT, args);
        validateNotEmptyArgs(CommandWord.EVENT, args);
        validateCommandWord(CommandWord.EVENT, args[0]);
        validateCommandWord(CommandWord.FROM, args[2]);
        validateDateTime(args[3]);
        validateCommandWord(CommandWord.TO, args[4]);
        validateDateTime(args[5]);
        validateStartDateBeforeEndDate(args[3], args[5]);
    }

    /**
     * Executes the `EventCommand`. It parses the command, validates it, and adds a new
     * event task to the task list if the command is valid.
     *
     * @param taskList The task list to which the event task is added.
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
        LocalDate startDate = Woof.parseDateTimeIn(args[3]);
        LocalDate endDate = Woof.parseDateTimeIn(args[5]);
        return taskList.addTask(new EventTask(description, startDate, endDate));
    }

}
