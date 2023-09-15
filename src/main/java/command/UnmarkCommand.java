package command;

import enums.CommandWord;
import enums.ExceptionMessage;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.TaskList;

/**
 * The `UnmarkCommand` class represents a command to unmark a task as done.
 * When executed, it parses the command, validates it, and marks the specified task as
 * undone in the task list if the command is valid.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs a new `UnmarkCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public UnmarkCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "unmark" command.
     * It checks if the command is correctly formatted and if the specified task index is valid.
     *
     * @param rawCommand The raw command string.
     * @param taskList   The task list against which to validate the task index.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *     error message.
     */
    public static void validate(String rawCommand, TaskList taskList) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";
        assert taskList != null : "task list cannot be null";

        String[] args = Parser.getArgs(rawCommand);

        try {
            taskList.validateTaskIndex(args[1]);
        } catch (Exception e) {
            throw new WoofInvalidCommandException(e.getMessage());
        }

        if (args.length != 2) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.UNMARK.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.UNMARK)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.UNMARK.getValue()
                )
            );
        }
    }

    /**
     * Executes the "unmark" command. It parses the command, validates it, and marks the specified task as
     * undone in the task list if the command is valid.
     *
     * @param taskList The task list in which the task is marked as undone.
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand, taskList);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }

        String[] args = Parser.getArgs(super.getRawCommand());
        String taskIndex = args[1];
        return taskList.markTaskUndone(taskIndex);
    }
}
