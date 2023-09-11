package harvard;
import java.util.ArrayList;
/**
 * Represents a list of tasks.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;
    /**
     * Constructs a TaskList object.
     */

    public TaskList() {
        tasks = new ArrayList<>();
    }
    /**
     * Constructs a TaskList object.
     * @param tasks The list of tasks.
     */

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */

    public void add(Task task) {
        tasks.add(task);
    }
    /**
     * Removes a task from the list.
     * @param index The index of the task to be removed.
     */

    public void remove(int index) {
        tasks.remove(index);
    }
    /**
     * Gets a task from the list.
     * @param index The index of the task to be retrieved.
     * @return The task.
     */

    public Task get(int index) {
        return tasks.get(index);
    }
    /**
     * Returns the size of tasks.
     * @return The size of tasks.
     */

    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks that match the keyword.
     * @param keyword The keyword to be searched.
     * @return The list of matching tasks.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                assert task != null : "Task should not be null";
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

}
