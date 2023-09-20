package commands;

import java.util.List;
import java.util.stream.Collectors;

import oop.Storage;
import oop.TaskList;
import oop.Ui;
import tasks.Task;

/**
 * The command that helps user search for tasks by using some search text.
 */
public class FindTasksCommand implements Command {
    private String searchText;
    public FindTasksCommand(String searchText) {
        this.searchText = searchText;
    }

    /**
     * {@inheritDoc}
     *
     * Executes the FindTasksCommand which filters out task based on a string prompt.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // used streams so that we can access the useful filter function.
        List<Task> filteredTasks = tasks.getTasks().stream().filter(t -> {
            return t.toString().contains(searchText);
        }).collect(Collectors.toList());
        // get UI to print out the filtered list of tasks
        int i = 0;
        String res = ui.getFindTaskMessage();
        for (Task task : filteredTasks) {
            res += ui.getTaskString(i, task);
            i++;
        }
        return res;
    }
}
