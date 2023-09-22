package duke;

import java.util.ArrayList;

/**
 * The `TaskList` class represents a collection of tasks in the Duke chatbot application.
 * It provides methods for adding, deleting, retrieving tasks, and obtaining the entire task list.
 */
public class TaskList {
    /**
     * The list that holds tasks.
     */
    private final ArrayList<Task> myList;

    /**
     * Constructs a new `TaskList` with the provided ArrayList of tasks.
     *
     * @param myList An ArrayList containing tasks to initialize the task list.
     */
    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.myList.add(t);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param i The index of the task to be deleted (1-based).
     */
    public void delete(int i) {
        int num = i - 1;
        this.myList.remove(num);
    }

    /**
     * Retrieves the entire list of tasks.
     *
     * @return An ArrayList containing all the tasks in the task list.
     */
    public ArrayList<Task> getList() {
        return this.myList;
    }

    /**
     * Retrieves a specific task from the task list at the specified index.
     *
     * @param i The index of the task to be retrieved (1-based).
     * @return The task at the specified index.
     */
    public Task get(int i) {
        int num = i - 1;
        return this.myList.get(num);
    }
}
