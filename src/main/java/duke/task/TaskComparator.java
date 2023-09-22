package duke.task;

import java.util.Comparator;

/**
 * Comparator for duke.task.Task instances.
 */
public class TaskComparator implements Comparator<Task> {
    /**
     * Compares two duke.task.Task instances.
     * If both are duke.task.Deadline instances, returns the one with the earlier deadline as the smaller element.
     * Else, if only one of them are duke.task.Deadline instances, returns the duke.task.Deadline instance
     * as the smaller element. Otherwise, returns both tasks as equal.
     *
     * @param task1 the first duke.task.Task instance to be compared.
     * @param task2 the second duke.task.Task instance to be compared.
     * @return an integer representing which task is smaller.
     */
    public int compare(Task task1, Task task2) {
        if (task1 instanceof Deadline && task2 instanceof Deadline) {
            Deadline deadline1 = (Deadline) task1;
            Deadline deadline2 = (Deadline) task2;
            return deadline1.compareDueDate(deadline2);
        } else if (task1 instanceof Deadline) {
            return -1;
        } else if (task2 instanceof Deadline) {
            return 1;
        } else {
            return 0;
        }
    }
}
