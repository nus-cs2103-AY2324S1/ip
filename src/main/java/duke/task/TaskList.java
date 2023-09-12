package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    /**
     * Adds a task to the task list.
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns a task from the task list.
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        assert index < taskList.size();
        return taskList.get(index);
    }

    /**
     * Removes a task from the task list.
     * @param task The task to remove.
     */
    public void removeTask(Task task) {
        assert taskList.contains(task);
        taskList.remove(task);
    }

    /**
     * Returns the size of the task list.
     * @return THe number of tasks in the list.
     */
    public int getTaskCount() {
        return taskList.size();
    }
}
