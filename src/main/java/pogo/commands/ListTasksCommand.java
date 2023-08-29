package pogo.commands;

import pogo.tasks.Task;

import java.time.LocalDateTime;

/**
 * Lists all the tasks in the task list.
 */
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final LocalDateTime from;
    private final LocalDateTime to;

    public ListTasksCommand(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Appends tasks that are between the specified dates to a string.
     *
     * @return CommandResult containing the tasks that are between the specified dates.
     */
    @Override
    public CommandResult execute() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isBetween(from, to)) {
                sb.append(i + 1).append(". ").append(task.getStatusMessage()).append("\n");
            }
        }
        return new CommandResult(sb.toString());
    }
}
