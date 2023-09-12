package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to delete the specified task.
 */
public class DeleteCommand extends ModifyTaskCommand {

    public static final String COMMAND_WORD = "delete";
    private static final String COMMAND_RESPONSE_SUCCESS = "Noted. I've removed this task:\n\t";

    public DeleteCommand(String taskIndex) {
        super(taskIndex);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.remove(taskIndex);
        storage.save(tasks);
        return COMMAND_RESPONSE_SUCCESS + task;
    }
}
