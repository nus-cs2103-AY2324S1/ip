package jarvis.parser;

import java.time.LocalDateTime;
import java.util.Comparator;

import jarvis.tasks.Task;

/**
 * Comparator class for comparing tasks based on their deadlines.
 * This class is used to sort tasks by their due dates, allowing for tasks
 * with earlier deadlines to appear first in sorted lists.
 */
public class DeadlineComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        LocalDateTime dueDate1 = task1.getDueDate();
        LocalDateTime dueDate2 = task2.getDueDate();
        if (dueDate1 == null && dueDate2 == null) {
            return 0;
        } else if (dueDate1 == null) { // Task2 has a deadline while Task1 doesn't, so Task2 comes first.
            return 1;
        } else if (dueDate2 == null) { // Task1 has a deadline while Task2 doesn't, so Task1 comes first.
            return -1;
        } else {
            // Compare tasks based on their deadlines.
            return dueDate1.compareTo(dueDate2);
        }
    }
}
