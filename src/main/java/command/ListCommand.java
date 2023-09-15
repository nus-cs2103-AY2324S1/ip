package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;

/**
 * The `ListCommand` class represents a command to list all tasks.
 * When executed, it validates the command and displays a list of all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new `ListCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public ListCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "list" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return "Invalid number of arguments for list command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.LIST)) {
            return "Invalid command word for list command.";
        }

        return ""; // Return an empty string if the command is valid
    }

    /**
     * Executes the "list" command. It validates the command and displays
     * a list of all tasks in the task list.
     *
     * @param taskList The task list from which tasks are listed.
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String validationError = validate(super.getRawCommand());
        if (isValidationError(validationError)) {
            return validationError;
        }
        return taskList.listAllTasks();
    }

}
