package noac;

import noac.task.Task;

import java.util.ArrayList;

/**
 * List of task to store all the tasks by the user.
 */
public class TaskList {


    private ArrayList<Task> taskList;

    /**
     * Create the TaskList object and initialise the empty arraylist.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Create the TaskList object and initialise the arraylist with the one given.
     *
     * @param taskList The arraylist to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     * Return the task at the index.
     *
     * @param index The index of the task to be returned
     * @return The task at that index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Add the input task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Remove a task in the list.
     *
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Get the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return taskList.size();
    }





}
