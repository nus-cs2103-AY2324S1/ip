package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;

/**
 * The `FindCommand` class represents a command to search for tasks based on a keyword in the Woof application.
 */
public class FindCommand extends Command {
    /**
     * Constructs a `FindCommand` with the given raw command.
     *
     * @param rawCommand The raw command input by the user.
     */
    public FindCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the `FindCommand` based on the raw command input.
     *
     * @param rawCommand The raw command input by the user.
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return "Invalid number of arguments for find command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.FIND)) {
            return "Invalid command word for find command.";
        }

        return ""; // Return an empty string if the command is valid
    }

    /**
     * Executes the `FindCommand` to search for tasks based on the specified keyword.
     *
     * @param taskList The task list in which to search for tasks.
     */
    public String execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        String validationError = validate(rawCommand);
        if (isValidationError(validationError)) {
            return validationError;
        }

        String[] args = Parser.getArgs(rawCommand);
        String keyword = args[1];
        return taskList.findTask(keyword);
    }

}
