import java.util.List;

/**
 * The TaskList class represents a collection of tasks.
 * It provides methods for adding, deleting, and accessing tasks within the list.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a new TaskList with a given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on its index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Retrieves the list of tasks stored in the TaskList.
     *
     * @return A List containing the tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a specific task from the TaskList based on its index.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTaskItem(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Prints the list of tasks to the console, displaying their indices and descriptions.
     */
    public void printTaskList() {
        for (int i = 0; i < this.getSize(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }
}
