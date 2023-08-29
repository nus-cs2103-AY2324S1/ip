package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Constructs a TaskList with existing tasks.
     *
     * @param tasks The initial list of tasks to populate the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list.addAll(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        this.list.remove(taskIndex);
    }

    /**
     * Retrieves a task from the TaskList based on the task index.
     *
     * @param taskIndex The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task getTask(int taskIndex) {
        return this.list.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the TaskList.
     */
    public int getLength() {
        return this.list.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return True if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.getLength() == 0;
    }

}
