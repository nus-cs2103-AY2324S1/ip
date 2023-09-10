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
     * @return `true` if the command is valid, `false` otherwise.
     */
    public static boolean validate(String rawCommand, TaskList taskList) {
        String[] args = Parser.getArgs(rawCommand);

        if (args.length != 2) {
            return false;
        }

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.DELETE)) {
            return false;
        }

        return taskList.validateTaskIndex(args[1]);
    }

    /**
     * Executes the "delete" command. It parses the command, validates it, and deletes
     * the specified task from the task list if the command is valid.
     *
     * @param taskList The task list from which the task is deleted.
     */
    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand, taskList)) {
            return;
        }
        String[] args = Parser.getArgs(rawCommand);
        taskList.deleteTask(args[1]);
    }
}
