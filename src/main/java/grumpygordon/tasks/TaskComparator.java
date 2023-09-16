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
        System.out.println("task1 desc: " + t1.toString());
        return t1.getDescription().compareTo(t2.getDescription());
    }
}
