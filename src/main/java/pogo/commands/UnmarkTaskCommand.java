package pogo.commands;

import pogo.tasks.Task;

public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private final int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index as not done.
     * @return CommandResult containing the status of the command.
     */
    @Override
    public CommandResult execute() {
        if (index < 0 || index >= tasks.size()) {
            return new CommandResult("Please enter a valid index.");
        }

        Task task = tasks.get(index);
        if (!task.isDone()) {
            return new CommandResult("This task is already not done.");
        }

        task.markAsUndone();
        return new CommandResult("Nice! I've marked this task as not done:\n" + task.getStatusMessage());
    }
}
