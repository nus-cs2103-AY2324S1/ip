package command;

import enums.CommandWord;
import enums.ExceptionMessage;
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
     * Validates the "list" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *     error message.
     */
    public static void validate(String rawCommand) throws WoofInvalidCommandException {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 1) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.LIST.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.LIST)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.LIST.getValue()
                )
            );
        }
    }


    /**
     * Executes the "list" command. It validates the command and displays
     * a list of all tasks in the task list.
     *
     * @param taskList The task list from which tasks are listed.
     */
    public String execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        try {
            validate(rawCommand);
        } catch (WoofInvalidCommandException e) {
            return e.getMessage();
        }
        return taskList.listAllTasks();
    }

}
