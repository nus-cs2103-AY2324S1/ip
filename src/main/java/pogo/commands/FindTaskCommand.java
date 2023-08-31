package pogo.commands;

import java.util.List;

import pogo.tasks.Task;
import pogo.tasks.TaskDescriptionFilter;

/**
 * Filters the task list based on a description.
 */
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String descriptionToFilter;

    /**
     * Creates a FindTaskCommand.
     *
     * @param descriptionToFilter The description to filter the task list with.
     */
    public FindTaskCommand(String descriptionToFilter) {
        this.descriptionToFilter = descriptionToFilter;
    }

    /**
     * Filters the task list based on the description.
     *
     * @return CommandResult containing the filtered task list.
     */
    public CommandResult execute() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        TaskDescriptionFilter tdf = new TaskDescriptionFilter(descriptionToFilter);
        List<Task> filteredTasks = tdf.filter(tasks);

        if (filteredTasks.size() == 0) {
            return new CommandResult("There are no tasks with that description.");
        }

        for (int i = 0; i < filteredTasks.size(); i++) {
            Task task = filteredTasks.get(i);
            sb.append(i + 1).append(". ").append(task.getStatusMessage()).append("\n");
        }
        return new CommandResult(sb.toString());
    }
}
