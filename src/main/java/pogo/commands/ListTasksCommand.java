package pogo.commands;

import java.time.LocalDateTime;

import pogo.tasks.Task;

/**
 * Lists all the tasks in the task list.
 */
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a ListTasksCommand object.
     *
     * @param from The start of the interval to list.
     * @param to The end of the interval to list.
     */
    public ListTasksCommand(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a string of tasks that are between the specified dates.
     *
     * @return CommandResult containing the tasks that are between the specified dates.
     */
    @Override
    public CommandResult execute() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        assert tasks != null : "Task list should not be null";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isBetween(from, to)) {
                sb.append(i + 1).append(". ").append(task.getStatusMessage()).append("\n");
            }
        }
        return new CommandResult(sb.toString());
    }
}
