package duke;

import java.time.LocalDateTime;
import java.util.Comparator;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;

/**
 * The TaskDateComparator class compares and sorts tasks based on their dates and times.
 */
public class TaskDateComparator implements Comparator<Task> {

    /**
     * Compares two tasks based on their dates.
     *
     * @param task1 The first task to compare.
     * @param task2 The second task to compare.
     * @return A negative integer, zero, or a positive integer as the first task is less than, equal to, or greater than
     * the second task, based on their dates.
     */
    @Override
    public int compare(Task task1, Task task2) {
        if (task1 instanceof ToDo && !(task2 instanceof ToDo)) {
            return -1;
        } else if (!(task1 instanceof ToDo) && task2 instanceof ToDo) {
            return 1;
        } else if (task1 instanceof Deadline && task2 instanceof Deadline) {
            LocalDateTime deadline1 = ((Deadline) task1).getBy();
            LocalDateTime deadline2 = ((Deadline) task2).getBy();
            return deadline1.compareTo(deadline2);
        } else if (task1 instanceof Event && task2 instanceof Event) {
            LocalDateTime start1 = ((Event) task1).getStart();
            LocalDateTime start2 = ((Event) task2).getStart();
            return start1.compareTo(start2);
        } else if (task1 instanceof Deadline && task2 instanceof Event) {
            LocalDateTime deadline = ((Deadline) task1).getBy();
            LocalDateTime start = ((Event) task2).getStart();
            return deadline.compareTo(start);
        } else if (task1 instanceof Event && task2 instanceof Deadline) {
            LocalDateTime start = ((Event) task1).getStart();
            LocalDateTime deadline = ((Deadline) task2).getBy();
            return start.compareTo(deadline);
        } else {
            return 0;
        }
    }
}
