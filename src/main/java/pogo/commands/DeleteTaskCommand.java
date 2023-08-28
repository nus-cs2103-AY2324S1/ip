package pogo.commands;

import pogo.common.Messages;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String DELETE_TASK_SUCCESS = "Noted. I've removed this task:\nThere are now %s tasks left.";
    private final int index;

    /**
     * Creates a DeleteTaskCommand object.
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

        tasks.remove(index);
        return new CommandResult(String.format(DELETE_TASK_SUCCESS, tasks.size()));
    }
}
