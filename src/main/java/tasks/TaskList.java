package tasks;

import java.util.ArrayList;

/**
 * Represents a list of Tasks object.
 */
public class TaskList {

    ArrayList<Task> tasks;

    /**
     * TaskList constructor that takes in an ArrayList<Task>.
     * @param tasks Arraylist of tasks object.
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Removes a task from the list.
     * @param n Index position of the task in the list.
     * @return The removed task.
     */
    public Task remTask(int n) {
        return tasks.remove(n);
    }

    /**
     * Adds a task into the list.
     * @param task The task that is being added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     *Checks if the list is empty.
     * @return True if the list is empty.
     */
    public boolean isTaskListEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets a task from the list.
     * @param n Index position of the wanted task in the list.
     * @return The wanted task.
     */
    public Task getTask (int n) {
        return tasks.get(n);
    }

    /**
     * Returns the size of the list.
     * @return List's size.
     */
    public int getSize() {
        return tasks.size();
    }
}
