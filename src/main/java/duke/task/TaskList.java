package duke.task;

import java.util.ArrayList;

/**
 * A Class that acts as an array for storing all the tasks.
 *
 * @author marioalvaro
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor if the taskList has not been created
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor if there is an existing taskList.
     *
     * @param taskList ArrayList of task.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add a task into the TaskList.
     *
     * @param task the task that want to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Remove a task from the TaskList
     *
     * @param index The index of the task that want to be removes.
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Get the task based on the index.
     *
     * @param index of the task
     * @return the Task with the index
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Get the size of the list
     * @return the size of the list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Get the index of a task.
     *
     * @param task The task
     * @return the index of the task
     */
    public int indexOf(Task task) {
        return this.taskList.indexOf(task);
    }


}
