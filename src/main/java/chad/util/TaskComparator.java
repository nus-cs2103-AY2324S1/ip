package chad.util;

import java.util.Comparator;

import chad.task.Task;

/**
 * Represents a comparator to compare 2 Task objects.
 */
public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return task1.compareTo(task2);
    }

}
