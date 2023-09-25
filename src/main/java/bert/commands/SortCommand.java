package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;

/**
 * Represents a command that sorts the task list.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    private static final String MESSAGE = "Here are the sorted tasks in your list:\n%1$s";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.sort();
        return new CommandResult(String.format(MESSAGE, tasks));
    }
}
