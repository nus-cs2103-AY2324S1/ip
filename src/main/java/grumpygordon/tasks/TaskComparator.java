package grumpygordon.tasks;

import java.util.Comparator;

/**
 * Represents a comparator for tasks.
 */
public class TaskComparator implements Comparator<Task> {
    /**
     * Compares two tasks.
     * @param t1 First task
     * @param t2 Second task
     * @return The comparison result
     */
    @Override
    public int compare(Task t1, Task t2) {
        return t1.toString().compareTo(t2.toString());
    }
}
