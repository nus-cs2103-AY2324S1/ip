package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;

/**
 * The `DeleteCommand` class represents a command to delete a task.
 * When executed, it parses the command, validates it, and deletes
 * the specified task from the task list if the command is valid.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new `DeleteCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public DeleteCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "delete" command.
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
            return "Invalid number of arguments for delete command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DELETE)) {
            return "Invalid command word for delete command.";
        }

        String taskListValidationError = taskList.validateTaskIndex(args[1]);
        if (taskList.isValidationError(taskListValidationError)) {
            return taskListValidationError;
        }


        return ""; // Return an empty string if the command is valid
    }


    /**
     * Executes the "delete" command. It parses the command, validates it, and deletes
     * the specified task from the task list if the command is valid.
     *
     * @param taskList The task list from which the task is deleted.
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String rawCommand = super.getRawCommand();
        String validationError = validate(rawCommand, taskList);

        if (isValidationError(validationError)) {
            return validationError; // Return the error message
        }

        String[] args = Parser.getArgs(rawCommand);
        return taskList.deleteTask(args[1]);
    }

}
