package bert.commands;

import bert.storage.Storage;
import bert.tasks.Task;
import bert.tasks.TaskList;

/**
 * Represents a command that adds a task.
 */
public class AddCommand extends Command {
    private static final String MESSAGE =
            "Got it. I've added this task:\n  %1$s\nNow you have %2$d tasks in the list.";
    private final Task toAdd;

    /**
     * Constructs an AddCommand instance containing the task to be added.
     *
     * @param toAdd The task to be added.
     */
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.add(this.toAdd);
        return new CommandResult(String.format(MESSAGE, this.toAdd, tasks.size()));
    }
}
