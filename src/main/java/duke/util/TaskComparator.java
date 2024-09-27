package duke.util;
import duke.task.*;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    public TaskComparator(){
    }
    public int compare(Task task1, Task task2) {
        return task1.compareTo(task2);
    }
}
