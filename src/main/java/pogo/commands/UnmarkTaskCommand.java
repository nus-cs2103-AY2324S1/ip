package pogo.commands;

import pogo.common.Messages;
import pogo.tasks.Task;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int index;

    /**
     * Creates a UnmarkTaskCommand object.
     *
     * @param index of the task to be marked as not done.
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @return CommandResult containing the status of the command.
     */
    @Override
    public CommandResult execute() {
        if (index < 0 || index >= tasks.size()) {
            return new CommandResult(Messages.INVALID_INDEX);
        }

        Task task = tasks.get(index);
        if (!task.isDone()) {
            return new CommandResult("This task is already not done.");
        }

        task.markAsUndone();
        return new CommandResult("Ok, I've marked this task as not done yet.\n" + task.getStatusMessage());
    }
}
