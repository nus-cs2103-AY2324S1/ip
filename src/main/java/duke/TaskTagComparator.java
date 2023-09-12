package duke;

import java.util.Comparator;

public class TaskTagComparator implements Comparator<Task>  {

    @Override
    public int compare(Task firstTask, Task secondTask) {
        return Task.tagValue(firstTask) - Task.tagValue(secondTask);
    }

}
