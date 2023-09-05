package command;

import parser.Parser;
import tasks.TaskList;
import enums.CommandWord;

/**
 * The `MarkCommand` class represents a command to mark a task as done.
 * When executed, it parses the command, validates it, and marks
 * the specified task as done in the task list if the command is valid.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a new `MarkCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public MarkCommand(String rawCommand) {
        super(rawCommand);
    }

    /**
     * Executes the "mark" command. It parses the command, validates it, and marks
     * the specified task as done in the task list if the command is valid.
     *
     * @param taskList The task list in which the task is marked as done.
     */
    public void execute(TaskList taskList) {
        String rawCommand = super.getRawCommand();
        if (!validate(rawCommand, taskList)) {
            return;
        }
        String[] args = Parser.getArgs(rawCommand);
        String taskIndex = args[1];
        taskList.markTaskDone(taskIndex);
    }

    /**
     * Validates the "mark" command.
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

        if (!CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.MARK)) {
            return false;
        }

        return taskList.validateTaskIndex(args[1]);
    }
}
