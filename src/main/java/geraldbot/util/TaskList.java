package geraldbot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import geraldbot.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ListManager<Task> {

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        super(taskList);
    }

    /**
     * Finds tasks in the list that match the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks matching the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        assert keyword != null : "Keyword for task search cannot be null.";
        return super.itemList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
