package pogo.commands;

import pogo.common.Messages;
import pogo.tasks.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String DELETE_TASK_SUCCESS = "Noted. I've removed this task:\n";
    private final int index;

    /**
     * Creates a DeleteTaskCommand object.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        if (index < 0 || index >= tasks.size()) {
            return new CommandResult(Messages.INVALID_INDEX);
        }

        Task task = tasks.remove(index);
        String tasksLeftMessage = String.format("There are %s tasks left.", tasks.size());
        return new CommandResult(DELETE_TASK_SUCCESS + task.getStatusMessage() + "\n"
            + tasksLeftMessage);
    }
}
