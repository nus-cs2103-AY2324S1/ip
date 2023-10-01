package socrates.command;

import socrates.data.exception.SocratesException;
import socrates.data.task.TaskList;
import socrates.storage.Storage;

/**
 * Represents a command to clear the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Clears all tasks.";
    private static final String COMMAND_RESPONSE = "Got it. I've cleared all tasks.";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws SocratesException {
        tasks.clear();
        storage.save(tasks);
        return new CommandResult(COMMAND_RESPONSE);
    }

}
