package duke.main;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks and provides methods for managing tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param taskList An ArrayList of tasks to populate the TaskList.
     */
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Retrieves all tasks in the TaskList.
     *
     * @return An ArrayList containing all tasks in the TaskList.
     */
    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Retrieves a task at a specific index in the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) { taskList.remove(index); }

    /**
     * Retrieves the total number of tasks in the TaskList.
     *
     * @return The total number of tasks in the TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markDone(int index) {
        taskList.get(index).markDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markNotDone(int index) {
        taskList.get(index).markNotDone();
    }

}
