package duke.commands;

import java.util.List;
import java.util.stream.Collectors;

import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find tasks based on certain patterns.
 */
public class FindCommand extends Command {
    private final String[] patterns;

    /**
     * Initializes the FindCommand with one or more search patterns.
     *
     * @param patterns The search patterns to look for in tasks' descriptions.
     */
    public FindCommand(String... patterns) {
        this.patterns = patterns;
    }

    /**
     * Executes the find operation by searching tasks that match the provided patterns
     * and adding the matched tasks' details to the UI.
     *
     * @param tasks   The list of tasks to search from.
     * @param ui      The user interface used to display the matched tasks.
     * @param storage The storage used to save tasks. (Not used in this context but present due to inheritance)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();

        for (String description : this.patterns) {
            TaskList matchedTaskList = findMatchedTasks(tasks, description);
            message.append(messageToReturn(matchedTaskList, description));
        }

        ui.addToResponse(message.toString());
    }

    /**
     * Filters tasks from a list based on a given pattern.
     *
     * @param tasks   The list of tasks to search from.
     * @param pattern The search pattern to look for in tasks' descriptions.
     * @return A list of tasks that match the search pattern.
     */
    private TaskList findMatchedTasks(TaskList tasks, String pattern) {
        List<Task> matchedTasks = tasks.getTasks().stream()
                .filter(task -> task.contains(pattern))
                .collect(Collectors.toList());

        TaskList matchedTaskList = new TaskList();
        matchedTasks.forEach(matchedTaskList::addTask);
        return matchedTaskList;
    }

    /**
     * Creates a message to be displayed based on whether tasks match the search pattern or not.
     *
     * @param matchedTaskList A list of tasks that match the search pattern.
     * @param pattern         The search pattern used for matching.
     * @return A message to be displayed to the user.
     */
    private String messageToReturn(TaskList matchedTaskList, String pattern) {
        if (!matchedTaskList.getTasks().isEmpty()) {
            String foundFormat = "Here are the tasks matching \"%s\" :\n";
            return String.format(foundFormat, pattern) + matchedTaskList + "\n";
        }

        String notFoundFormat = "No tasks matching \"%s\" were found.\n";
        return String.format(notFoundFormat, pattern);
    }
}


