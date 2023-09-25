package command;

import enums.CommandWord;
import exceptions.WoofInvalidCommandException;
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
     * Validates the `DeleteCommand`.
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
        validateArgsLengthEquals(CommandWord.DELETE, args, 2);
        validateNotNullArgs(CommandWord.DELETE, args);
        validateNotEmptyArgs(CommandWord.DELETE, args);
        validateCommandWord(CommandWord.DELETE, args[0]);
        validateTaskIndex(taskList, args[1]);
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
        try {
            validate(rawCommand, taskList);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }

        String[] args = Parser.getArgs(rawCommand);
        return taskList.deleteTask(args[1]);
    }

}
