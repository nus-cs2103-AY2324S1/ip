package arona.task;

import java.util.ArrayList;

import arona.storage.Storage;

/**
 * Represents a list of tasks in Arona. The task list stores tasks in an ArrayList and
 * is typically used to manage tasks throughout the application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new task list by loading tasks from the provided storage.
     *
     * @param storage The storage object responsible for loading tasks into the list.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        storage.loadTasks(this.tasks);
    }

    /**
<<<<<<< HEAD
     * Retrieves the ArrayList of tasks stored in the task list.
     *
     * @return An ArrayList of tasks.
=======
     * Constructs an empty TaskList.
>>>>>>> branch-Level-9
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

<<<<<<< HEAD
=======
    /**
     * Retrieves the ArrayList of tasks stored in the task list.
     *
     * @return An ArrayList of tasks.
     */
>>>>>>> branch-Level-9
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
