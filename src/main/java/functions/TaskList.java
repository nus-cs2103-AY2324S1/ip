package functions;

import java.util.ArrayList;

import tasks.Task;

/**
 * A class for storing a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * A public constructor to initialize a new TaskList instance
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param idx the index of the task to return
     * @return the task at the specified index
     */
    public Task get(int idx) {
        return this.taskList.get(idx);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Removes the task at the specified index from the list.
     *
     * @param idx the index of the task to remove
     */
    public void remove(int idx) {
        this.taskList.remove(idx);
    }

}
