package seedu.duke;

import java.util.Comparator;
import java.time.LocalDate;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        
        if (task1 instanceof Todo && task2 instanceof Todo) {
            return task1.toString().compareTo(task2.toString()); 
        }
        if (task1 instanceof Todo) {
            return 1;
        } else if (task2 instanceof Todo) {
            return -1;
        }
        LocalDate date1;
        LocalDate date2;
        if (task1 instanceof Deadline) {
            Deadline deadline = (Deadline) task1;
            date1 = deadline.by;
        } else {
            Event event = (Event) task1;
            date1 = event.from;
        }
        if (task2 instanceof Deadline) {
            Deadline deadline = (Deadline) task2;
            date2 = deadline.by;
        } else {
            Event event = (Event) task2;
            date2 = event.from;
        }
        return date1.compareTo(date2);
    }
}
