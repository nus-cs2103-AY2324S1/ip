package command;

import enums.CommandWord;
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
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand, TaskList taskList) {
        assert rawCommand != null : "raw command cannot be null";
        assert taskList != null : "task list cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return "Invalid number of arguments for unmark command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.UNMARK)) {
            return "Invalid command word for unmark command.";
        }

        String taskListValidationError = taskList.validateTaskIndex(args[1]);
        if (taskList.isValidationError(taskListValidationError)) {
            return taskListValidationError;
        }

        return ""; // Return an empty string if the command is valid
    }

    /**
     * Executes the "unmark" command. It parses the command, validates it, and marks the specified task as
     * undone in the task list if the command is valid.
     *
     * @param taskList The task list in which the task is marked as undone.
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String validationError = validate(super.getRawCommand(), taskList);
        if (isValidationError(validationError)) {
            return validationError;
        }
        String[] args = Parser.getArgs(super.getRawCommand());
        String taskIndex = args[1];
        return taskList.markTaskUndone(taskIndex);
    }
}
