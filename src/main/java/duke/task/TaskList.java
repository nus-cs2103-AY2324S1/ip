package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }
    public int getTaskCount() {
        return taskList.size();
    }
}
