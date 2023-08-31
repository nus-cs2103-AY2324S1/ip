package pogo.tasks;

import java.util.List;

/**
 * Interface for a filter on a List of tasks.
 */
interface TaskFilter {
    /**
     * Filters a list of tasks based on some specific criteria.
     *
     * @param tasks The list of tasks to be filtered.
     * @return The filtered tasks.
     */
    List<Task> filter(List<Task> tasks);
}
