package command;

import enums.CommandWord;
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
     * @return `true` if the command is valid, `false` otherwise.
     */
    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 1) {
            return false;
        }

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.LIST);
    }

    /**
     * Executes the "list" command. It validates the command and displays
     * a list of all tasks in the task list.
     *
     * @param taskList The task list from which tasks are listed.
     */
    public void execute(TaskList taskList) {
        if (!validate(super.getRawCommand())) {
            return;
        }
        taskList.listAllTasks();
    }
}
