package milbot;

import java.util.ArrayList;
import java.util.List;
import taskclasses.Task;

/**
 * TaskList class represents a list of tasks and provides methods to manage tasks.
 */
public class TaskList {
    private List<Task> taskList;
    /**
     * Constructs a TaskList instance with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }
    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return taskList.size();
    }
    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }
}
