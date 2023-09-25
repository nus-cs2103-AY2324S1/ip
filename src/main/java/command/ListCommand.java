package command;

import enums.CommandWord;
import exceptions.WoofInvalidCommandException;
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
     * Validates the `ListCommand`.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthEquals(CommandWord.LIST, args, 1);
        validateNotNullArgs(CommandWord.LIST, args);
        validateNotEmptyArgs(CommandWord.LIST, args);
        validateCommandWord(CommandWord.LIST, args[0]);
    }


    /**
     * Executes the `ListCommand`. It validates the command and displays
     * a list of all tasks in the task list.
     *
     * @param taskList The task list from which tasks are listed.
     */
    public String execute(TaskList taskList) {
        assert taskList != null : "task list cannot be null";

        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }
        return taskList.listAllTasks();
    }

}
