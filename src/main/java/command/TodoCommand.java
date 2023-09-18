package command;

import enums.CommandWord;
import exceptions.WoofInvalidCommandException;
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
     * Validates the `TodoCommand`.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthEquals(CommandWord.TODO, args, 2);
        validateNotNullArgs(CommandWord.TODO, args);
        validateNotEmptyArgs(CommandWord.TODO, args);
        validateCommandWord(CommandWord.TODO, args[0]);
    }

    /**
     * Executes the `TodoCommand`. It parses the command, validates it, and adds a new
     * todo task to the task list if the command is valid.
     *
     * @param taskList The task list to which the todo task is added.
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
        return taskList.addTask(new TodoTask(description));
    }
}
