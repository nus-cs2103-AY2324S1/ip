package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class represents a collection of tasks and provides methods to manage them.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task object to the TaskList
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        tasks.remove(task);
    }

    /**
     * Retrieves the list of tasks in the TaskList.
     *
     * @return A List containing the tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task at the given index in the TaskList.
     *
     * @param index THe index of the task to be retrieved.
     * @return The Task at the given index.
     */
    public Task getTaskAtIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieve the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }
}