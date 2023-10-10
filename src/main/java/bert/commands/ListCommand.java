package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;

/**
 * Represents a command that prints out the list of tasks in a task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String MESSAGE = "Here are the tasks in your list:\n%1$s";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(String.format(MESSAGE, tasks));
    }
}
