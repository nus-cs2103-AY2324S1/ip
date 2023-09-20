package jarvis.parser;

import java.time.LocalDateTime;
import java.util.Comparator;

import jarvis.tasks.Task;


public class DeadlineComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        LocalDateTime dueDate1 = task1.getDueDate();
        LocalDateTime dueDate2 = task2.getDueDate();
        if (dueDate1 == null || dueDate2 == null) {
            return 0;
        }
        return dueDate1.compareTo(dueDate2);
    }
}
