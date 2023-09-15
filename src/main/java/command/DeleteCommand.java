package command;

import enums.CommandWord;
import enums.ExceptionMessage;
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
     * Validates the "delete" command.
     * It checks if the command is correctly formatted and if the specified task index is valid.
     *
     * @param rawCommand The raw command string.
     * @param taskList   The task list against which to validate the task index.
     * @throws WoofInvalidCommandException If the command is invalid, it throws a woof invalid command exception with an
     *     error message.
     */
    public static void validate(String rawCommand, TaskList taskList) throws WoofInvalidCommandException {
        String[] args = Parser.getArgs(rawCommand);

        try {
            taskList.validateTaskIndex(args[1]);
        } catch (Exception e) {
            throw new WoofInvalidCommandException(e.getMessage());
        }

        if (args.length != 2) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_NUMBER_OF_ARGUMENTS.getValueFormat(
                    CommandWord.DELETE.getValue()
                )
            );
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DELETE)) {
            throw new WoofInvalidCommandException(
                ExceptionMessage.INVALID_COMMAND_WORD.getValueFormat(
                    CommandWord.DELETE.getValue()
                )
            );
        }
    }



    /**
     * Executes the "delete" command. It parses the command, validates it, and deletes
     * the specified task from the task list if the command is valid.
     *
     * @param taskList The task list from which the task is deleted.
     */
    public String execute(TaskList taskList) {
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
