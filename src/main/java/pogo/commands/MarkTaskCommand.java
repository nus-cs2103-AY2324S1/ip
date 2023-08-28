package pogo.commands;

import pogo.tasks.Task;

public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index as done.
     * @return CommandResult containing the status of the command.
     */
    @Override
    public CommandResult execute() {
        if (index < 0 || index >= tasks.size()) {
            return new CommandResult("Please enter a valid index.");
        }

        Task task = tasks.get(index);
        if (task.isDone()) {
            return new CommandResult("This task is already done.");
        }

        task.markAsDone();
        return new CommandResult("Nice! I've marked this task as done:\n" + task.getStatusMessage());
    }
}
