package pogo.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        // Matches partially if the description contains the description to filter.
        // E.g. "Hor" matches "Homework", but not "Hero".
        Predicate<Task> notPartialMatcher = task -> {
            String description = task.getDescription();

            int i = 0;
            int j = 0;
            while (i < this.descriptionToFilter.length() && j < description.length()) {
                if (this.descriptionToFilter.charAt(i) == description.charAt(j)) {
                    i++;
                }
                j++;
            }

            return i != this.descriptionToFilter.length();
        };
        out.removeIf(notPartialMatcher);
        return out;
    }
}
