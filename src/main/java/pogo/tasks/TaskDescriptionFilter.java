package pogo.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Filters a task based on its description.
 */
public class TaskDescriptionFilter implements TaskFilter {
    private final String descriptionToFilter;

    public TaskDescriptionFilter(String descriptionToFilter) {
        this.descriptionToFilter = descriptionToFilter;
    }

    /**
     * Filters the list of tasks based on the description of the task.
     * If the task description contains the description to filter, it is kept.
     * The original list is not modified.
     */
    @Override
    public List<Task> filter(List<Task> tasks) {
        ArrayList<Task> out = new ArrayList<>(tasks);
        out.removeIf(task -> !task.getDescription().contains(descriptionToFilter));
        return out;
    }
}
