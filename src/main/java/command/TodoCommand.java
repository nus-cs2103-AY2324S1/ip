package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import tasks.TodoTask;

/**
 * The `TodoCommand` class represents a command to create a new todo task.
 * When executed, it parses the command, validates it, and adds
 * a new todo task to the task list if the command is valid.
 */
public class TodoCommand extends Command {
    /**
     * Constructs a new `TodoCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public TodoCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Validates the "todo" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return An empty string if the command is valid, or an error message if it's invalid.
     */
    public static String validate(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return "Invalid number of arguments for todo command.";
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.TODO)) {
            return "Invalid command word for todo command.";
        }

        return ""; // Return an empty string if the command is valid
    }

    /**
     * Executes the "todo" command. It parses the command, validates it, and adds a new
     * todo task to the task list if the command is valid.
     *
     * @param taskList The task list to which the todo task is added.
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String rawCommand = super.getRawCommand();
        String validationError = validate(rawCommand);
        if (isValidationError(validationError)) {
            return validationError;
        }

        String[] args = Parser.getArgs(rawCommand);
        String description = args[1];
        return taskList.addTask(new TodoTask(description));
    }
}
