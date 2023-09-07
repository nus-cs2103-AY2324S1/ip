package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;

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
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.add(toAdd);
        storage.save(tasks);
        return getCommandResponse(tasks);
    }
}
