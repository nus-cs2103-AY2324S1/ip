package nexus.task;

import java.util.ArrayList;

/**
 * Class that contains all the tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Create TaskList given a list.
     *
     * @param list ArrayList
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Getter for tasks.
     *
     * @param index Index of the task
     * @return The task retrieved
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Get the size of the list.
     *
     * @return An int that is the size of list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Add task.
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Remove task.
     *
     * @param index Index of task to be removed
     */
    public void remove(int index) {
        this.list.remove(index);
    }
}
