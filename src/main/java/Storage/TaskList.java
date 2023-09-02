package storage;

import taskmanager.Tasks;

import java.util.ArrayList;

/**
 * The `TaskList` class behave like an ArrayList
 * to store a list of tasks.
 */
public class TaskList {
    private ArrayList<Tasks> task; //The array list to store all the tasks.

    /**
     * Constructs a `TaskList` with the specified list of tasks.
     *
     * @param task The ArrayList containing tasks to initialize the task list.
     */
    public TaskList(ArrayList<Tasks> task) {
        this.task = task;
    }

    /**
     * Adds task to task list.
     *
     * @param t The task to be added to the list.
     */
    public void add(Tasks t) {
        task.add(t);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return task.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty; otherwise, false.
     */
    public boolean isEmpty() {
        return task.isEmpty();
    }

    /**
     * Removes a task at the specified index from the task list.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {task.remove(i);}

    /**
     * Returns the ArrayList of tasks in the task list.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Tasks> getAllTasks() {
        return task;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the index.
     */
    public Tasks get(int index) {
        return task.get(index);
    }

    /**
     * Compares this `TaskList` to another `TaskList` for equality.
     *
     * @param other The `TaskList` to compare to this one.
     * @return True if the `TaskList` objects are equal; otherwise, false.
     */
    public boolean taskListEqual(TaskList other) {
        if (task.size() != other.task.size()) {
            return false;
        }

        for (int i = 0; i < task.size(); i++) {
            if (!task.get(i).equals(other.task.get(i))) {
                return false;
            }
        }

        return true;
    }

}
