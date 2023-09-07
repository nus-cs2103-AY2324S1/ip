package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to clear the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    private static final String COMMAND_RESPONSE = "Got it. I've cleared all tasks.";

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        return COMMAND_RESPONSE;
    }
}
