package evo.tasks;

import java.util.ArrayList;

/**
 * The TaskList class represents an ArrayList of tasks.
 * It provides methods for managing and interacting with the list of tasks.
 */
public class TaskList {
    /**
     * An ArrayList to store the list of tasks.
     */
    protected ArrayList<Task> tasksList;

    /**
     * Constructs an empty TaskList.
     * Initializes an empty ArrayList to store tasks.
     */
    public TaskList() {
        ArrayList<Task> tasksList = new ArrayList<>();
        this.tasksList = tasksList;
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasksList The ArrayList of Task objects to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Gets a task from the TaskList at a specific index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the provided index is out of the valid range of task indices.
     */
    public Task get(int index) {
        if (index < 0 || index >= this.tasksList.size()) {
            throw new IndexOutOfBoundsException("Please input a valid task's index number.");
        }
        return this.tasksList.get(index);
    }

    /**
     * Retrieves the description of a task at the specified index in the task list.
     *
     * @param index The index of the task in the task list.
     * @return The description of the task at the specified index.
     * @throws IndexOutOfBoundsException If the provided index is out of the valid range of task indices.
     */
    public String getDescription(int index) {
        if (index < 0 || index >= this.tasksList.size()) {
            throw new IndexOutOfBoundsException("Please input a valid task's index number.");
        }
        return this.tasksList.get(index).description;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int tasksListLength() {
        return this.tasksList.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if the TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasksList.isEmpty();
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The Task object to be added to the TaskList.
     */
    public void addTask(Task task) {
        tasksList.add(task);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param taskNumberToDelete The index of the task to be deleted.
     */
    public void deleteTask(int taskNumberToDelete) {
        // Delete a task from taskList
        this.tasksList.remove(taskNumberToDelete);
    }
}
