package command;

import enums.CommandWord;
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
     * Validates the "mark" command.
     * It checks if the command is correctly formatted and if the specified task index is valid.
     *
     * @param rawCommand The raw command string.
     * @param taskList   The task list against which to validate the task index.
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand, TaskList taskList) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return "Invalid number of arguments for mark command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.MARK)) {
            return "Invalid command word for mark command.";
        }

        String taskListValidationError = taskList.validateTaskIndex(args[1]);
        if (taskList.isValidationError(taskListValidationError)) {
            return taskListValidationError;
        }

        return ""; // Return an empty string if the command is valid
    }

    /**
     * Executes the "mark" command. It parses the command, validates it, and marks
     * the specified task as done in the task list if the command is valid.
     *
     * @param taskList The task list in which the task is marked as done.
     */
    public String execute(TaskList taskList) {
        String validationError = validate(super.getRawCommand(), taskList);
        if (isValidationError(validationError)) {
            return validationError;
        }

        String[] args = Parser.getArgs(super.getRawCommand());
        String taskIndex = args[1];
        return taskList.markTaskDone(taskIndex);
    }

}
