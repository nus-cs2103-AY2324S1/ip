package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to clear the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Clears all tasks.";
    private static final String COMMAND_RESPONSE = "Got it. I've cleared all tasks.";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        return new CommandResult(COMMAND_RESPONSE);
    }

}
