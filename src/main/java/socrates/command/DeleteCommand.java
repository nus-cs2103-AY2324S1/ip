package socrates.command;

import socrates.data.exception.SocratesException;
import socrates.data.task.Task;
import socrates.data.task.TaskList;
import socrates.storage.Storage;

/**
 * Represents a command to delete the specified task.
 */
public class DeleteCommand extends ModifyTaskCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Deletes the given task.";
    private static final String COMMAND_RESPONSE_SUCCESS = "Noted. I've removed this task:\n\t";

    public DeleteCommand(String taskIndex) {
        super(taskIndex);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws SocratesException {
        Task task = tasks.remove(taskIndex);
        storage.save(tasks);
        return new CommandResult(COMMAND_RESPONSE_SUCCESS + task);
    }

}
