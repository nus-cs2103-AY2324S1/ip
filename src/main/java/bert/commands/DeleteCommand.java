package bert.commands;

import bert.storage.Storage;
import bert.tasks.Task;
import bert.tasks.TaskList;

/**
 * Represents a command that deletes a task in a task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE =
            "Noted. I've removed this task:\n  %1$s\nNow you have %2$d tasks in the list.";
    private final int index;

    /**
     * Constructs a DeleteCommand instance containing the index of a task to be deleted.
     *
     * @param index The index of a specific task in a task list.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Task deletedTask = tasks.delete(this.index);
        assert deletedTask != null : "Task should not be null";
        return new CommandResult(String.format(MESSAGE, deletedTask, tasks.size()));
    }
}
