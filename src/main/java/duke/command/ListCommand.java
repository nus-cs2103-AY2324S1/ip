package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    private static final String COMMAND_RESPONSE = "Here are the list of tasks:\n";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return COMMAND_RESPONSE + tasks.getFormattedList();
    }
}
