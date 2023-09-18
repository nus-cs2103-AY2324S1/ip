package command;

import java.time.LocalDate;

import enums.CommandWord;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.DeadlineTask;
import tasks.TaskList;

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
     * Validates the `DeadlineCommand`.
     * It checks if the command is correctly formatted and if the specified task index is valid.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthEquals(CommandWord.DEADLINE, args, 4);
        validateNotNullArgs(CommandWord.DEADLINE, args);
        validateNotEmptyArgs(CommandWord.DEADLINE, args);
        validateCommandWord(CommandWord.DEADLINE, args[0]);
        validateCommandWord(CommandWord.BY, args[2]);
        validateDateTime(args[3]);
    }

    /**
     * Executes the `DeadlineCommand`. It parses the command, validates it, and adds a new
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
        LocalDate endDate = parseDateTimeIn(args[3]);
        return taskList.addTask(new DeadlineTask(description, endDate));
    }

}
