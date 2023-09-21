package jarvis.tasklist;

import jarvis.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks, providing utility methods for managing tasks.
 * This class provides a convenient way to handle tasks, such as adding, removing, and retrieving tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initializes a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a task list with a given list of tasks.
     *
     * @param loadedTasks An ArrayList of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task remove(int index){
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Provides access to the entire list of tasks.
     *
     * @return An ArrayList containing all tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}