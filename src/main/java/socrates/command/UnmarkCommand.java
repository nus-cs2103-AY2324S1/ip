package socrates.command;

import socrates.data.exception.SocratesException;
import socrates.data.task.Task;
import socrates.data.task.TaskList;
import socrates.storage.Storage;

/**
 * Represents a command to mark the specified task as not done yet.
 */
public class UnmarkCommand extends ModifyTaskCommand {

    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": Marks the given task as not done yet.";
    private static final String COMMAND_RESPONSE = "Nice! I've marked this task as not done yet:\n\t";

    public UnmarkCommand(String taskIndex) {
        super(taskIndex);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws SocratesException {
        Task task = tasks.unmark(taskIndex);
        storage.save(tasks);
        return new CommandResult(COMMAND_RESPONSE + task);
    }

}
