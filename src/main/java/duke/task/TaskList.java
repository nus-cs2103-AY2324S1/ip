package duke.task;

import java.util.ArrayList;

/**
 * Represent the list of tasks.
 *
 * @author Armando Jovan Kusuma
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a task list which is empty at first.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task the task to be added into the task list.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Gets a specific task from the task list.
     *
     * @param index the index of the task to be retrieved.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a specific task from the task list.
     *
     * @param index the index of the task to be removed.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the task list.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

//    /**
//     * Gets the number of tasks in the task list
//     */
//    public int getLength() {
//        return tasks.size();
//    }

}
