package duke;

import java.util.Comparator;

/**
 * Comparator object which compares two tasks based off their tag value.
 */
public class TaskTagComparator implements Comparator<Task> {

    /**
     * @inheritDoc
     */
    @Override
    public int compare(Task firstTask, Task secondTask) {
        return Task.tagValue(firstTask) - Task.tagValue(secondTask);
    }

}
