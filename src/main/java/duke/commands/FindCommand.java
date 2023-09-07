package duke.commands;

import java.util.stream.Collectors;
import java.util.List;

import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A FindCommand class that take in a description and find all tasks that match the given
 * description.
 */
public class FindCommand extends Command {
    private final String[] patterns;

    public FindCommand(String... patterns) {
        this.patterns = patterns;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder();

        for (String description : this.patterns) {
            TaskList matchedTaskList = findMatchedTasks(tasks, description);
            message.append(messagetoReturn(matchedTaskList, description));
        }

        ui.appendResponse(message.toString());
    }

    private TaskList findMatchedTasks(TaskList tasks, String pattern) {
        List<Task> matchedTasks = tasks.getTasks().stream()
                .filter(task -> task.contains(pattern))
                .collect(Collectors.toList());

        TaskList matchedTaskList = new TaskList();
        matchedTasks.forEach(matchedTaskList::addTask);
        return matchedTaskList;
    }


    private String messagetoReturn(TaskList matchedTaskList, String pattern) {

        if (!matchedTaskList.getTasks().isEmpty()) {
            String foundFormat = "Here are the tasks matching \"%s\" :\n";
            return String.format(foundFormat, pattern) + matchedTaskList + "\n";
        }

        String notFoundFormat = "No tasks matching \"%s\" were found.\n";
        return String.format(notFoundFormat, pattern);
    }
}
