package command;

import enums.CommandWord;
import exceptions.WoofInvalidCommandException;
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
     * Validates the `FindCommand`.
     *
     * @param rawCommand The raw command input by the user.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *                                     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        assert rawCommand != null : "raw command cannot be null";

        String[] args = Parser.getArgs(rawCommand);
        validateArgsLengthMoreThanEquals(CommandWord.FIND, args, 2);
        validateNotNullArgs(CommandWord.FIND, args);
        validateNotEmptyArgs(CommandWord.FIND, args);
        validateCommandWord(CommandWord.FIND, args[0]);
    }


    /**
     * Executes the `FindCommand` to search for tasks based on the specified keyword.
     *
     * @param taskList The task list in which to search for tasks.
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
        String keySentence = args[1];
        return taskList.findTask(keySentence.split("\\s+"));
    }
}
