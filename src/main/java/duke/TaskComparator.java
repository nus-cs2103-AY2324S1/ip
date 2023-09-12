package duke;

import duke.task.Task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t2.getPriority() - t1.getPriority();
    }
}
