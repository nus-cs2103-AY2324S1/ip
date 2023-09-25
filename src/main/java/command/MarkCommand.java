package command;

import enums.CommandWord;
import exceptions.WoofInvalidCommandException;
import parser.Parser;
import tasks.TaskList;

/**
 * The `MarkCommand` class represents a command to mark a task as done.
 * When executed, it parses the command, validates it, and marks
 * the specified task as done in the task list if the command is valid.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a new `MarkCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public MarkCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the `MarkCommand`.
     * It checks if the command is correctly formatted and if the specified task index is valid.
     *
     * @param rawCommand The raw command string.
     * @param taskList   The task list against which to validate the task index.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand, TaskList taskList) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";
        assert taskList != null : "task list cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthEquals(CommandWord.MARK, args, 2);
        validateNotNullArgs(CommandWord.MARK, args);
        validateNotEmptyArgs(CommandWord.MARK, args);
        validateCommandWord(CommandWord.MARK, args[0]);
        validateTaskIndex(taskList, args[1]);
    }


    /**
     * Executes the `MarkCommand`. It parses the command, validates it, and marks
     * the specified task as done in the task list if the command is valid.
     *
     * @param taskList The task list in which the task is marked as done.
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
        return taskList.markTaskDone(taskIndex);
    }

}
