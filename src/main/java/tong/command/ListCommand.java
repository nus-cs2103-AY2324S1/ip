package tong.command;

import tong.command.Command;
import tong.command.CommandResult;

/**
 * Lists all tong.task.Task in the tong.TaskList to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks in the tasklist with their index numbers, types and markings.\n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public CommandResult execute() {
        return new CommandResult(taskList.toString());
    }
}