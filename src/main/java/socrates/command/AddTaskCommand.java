package socrates.command;

import socrates.data.exception.SocratesException;
import socrates.data.task.Task;
import socrates.data.task.TaskList;
import socrates.storage.Storage;

/**
 * Represents a command to add a new task.
 */
public abstract class AddTaskCommand extends Command {

    private static final String COMMAND_RESPONSE_FORMAT =
            "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.";

    protected Task toAdd;

    protected String getCommandResponse(TaskList tasks) {
        return String.format(COMMAND_RESPONSE_FORMAT, toAdd.toString(), tasks.size());
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws SocratesException {
        tasks.add(toAdd);
        storage.save(tasks);
        return new CommandResult(getCommandResponse(tasks));
    }
}
