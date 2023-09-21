package duke.task;

import java.util.Comparator;

/**
 * The PriorityComparator class is a custom comparator for comparing tasks based on their priorities.
 * It implements the Comparator interface to provide a custom comparison method for tasks.
 */
public class PriorityComparator implements Comparator<Task> {

    /**
     * Compares two tasks based on their priorities, task types, and descriptions.
     *
     * @param task1 The first task to compare.
     * @param task2 The second task to compare.
     * @return A negative integer, zero, or a positive integer as the first task is less than, equal to,
     *         or greater than the second task, respectively.
     */
    @Override
    public int compare(Task task1, Task task2) {
        TaskPriority priority1 = task1.getPriority();
        TaskPriority priority2 = task2.getPriority();

        // First, compare priorities based on their ordinal values
        int priorityComparison = Integer.compare(priority1.ordinal(), priority2.ordinal());

        if (priorityComparison == 0) {
            // If priorities are the same, compare task types
            TaskType type1 = task1.getType();
            TaskType type2 = task2.getType();

            // Compare task types based on their ordinal values
            int typeComparison = Integer.compare(type1.ordinal(), type2.ordinal());

            if (typeComparison == 0) {
                // If task types are the same, compare by other criteria (e.g., description)
                return task1.getDescription().compareTo(task2.getDescription());
            } else {
                return typeComparison;
            }
        }

        return priorityComparison;
    }
}
