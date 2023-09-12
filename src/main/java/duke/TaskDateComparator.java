package duke;

import java.util.Comparator;

public class TaskDateComparator implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        if (firstTask instanceof Deadline && secondTask instanceof Deadline) {
            Deadline firstDeadline = (Deadline) firstTask;
            Deadline secondDeadline = (Deadline) secondTask;
            return firstDeadline.deadline.compareTo(secondDeadline.deadline);
        }
        if (firstTask instanceof Event && secondTask instanceof Event) {
            Event firstDeadline = (Event) firstTask;
            Event secondDeadline = (Event) secondTask;
            return firstDeadline.start.compareTo(secondDeadline.start);
        }
        return -1;
    }
}
