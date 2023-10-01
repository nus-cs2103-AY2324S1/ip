package socrates.command;

import socrates.data.task.TaskList;
import socrates.storage.Storage;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Lists all tasks.";
    private static final String COMMAND_RESPONSE = "Here are the list of tasks:\n";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(COMMAND_RESPONSE + tasks.getFormattedList());
    }

}
