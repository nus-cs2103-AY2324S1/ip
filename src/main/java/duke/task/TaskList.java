package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 *
 * @author Joseph Oliver Lim
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList that is initially empty.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList that contains several tasks.
     *
     * @param tasks A list of tasks to be entered into the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task into the TaskList.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a Task from the TaskList.
     *
     * @param index The index of the task to be retrieved.
     * @return A Task that is retrieved.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getCountTasks() {
        return this.tasks.size();
    }
}
