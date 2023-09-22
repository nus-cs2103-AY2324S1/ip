package jarvis.parser;

import java.util.Comparator;

import jarvis.tasks.Deadline;
import jarvis.tasks.Task;


/**
 * Comparator class for comparing tasks based on their deadlines.
 * This class is used to sort tasks by their due dates, allowing for tasks
 * with earlier deadlines to appear first in sorted lists.
 */
public class DeadlineComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        if (task1 instanceof Deadline && task2 instanceof Deadline) {
            Deadline deadlineTask1 = (Deadline) task1;
            Deadline deadlineTask2 = (Deadline) task2;
            if (deadlineTask1.getDueDateTime() == null && deadlineTask2.getDueDateTime() == null) {
                return 0;
            } else if (deadlineTask1.getDueDateTime() == null) {
                return 1;
            } else if (deadlineTask2.getDueDateTime() == null) {
                return -1;
            } else {
                // Compare tasks based on their deadlines.
                return deadlineTask1.getDueDateTime().compareTo(deadlineTask2.getDueDateTime());
            }
        } else {
            // If one of the tasks is not a Deadline, place it before the Deadline task.
            if (task1 instanceof Deadline) {
                return -1;
            } else if (task2 instanceof Deadline) {
                return 1;
            } else {
                // Both tasks are not Deadlines, their order doesn't matter.
                return 0;
            }
        }
    }
}
