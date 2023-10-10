package duke;

import java.util.ArrayList;

/**
 * Class to represent a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Initialises a task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialises a task list using another ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size.
     */
    public int listSize() {
        return taskList.size();
    }

    /**
     * Returns a specific task from the task list
     * using the task index.
     *
     * @param taskIndex Index of the task to be fetched.
     * @return Task that matches the index.
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }
}

