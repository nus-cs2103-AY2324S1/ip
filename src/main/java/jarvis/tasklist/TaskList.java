package jarvis.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jarvis.task.Deadline;
import jarvis.task.Task;

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
        assert task != null : "Task to be added should not be null";

        tasks.add(task);
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task remove(int index) {
        assert isValidIndex(index) : "Invalid task index for retrieval";

        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        assert isValidIndex(index) : "Invalid task index for retrieval";

        return tasks.get(index);
    }

    /**
     * Checks if the list contains the specified task.
     *
     * @param task The task to be checked.
     * @return True if the task is in the list, false otherwise.
     */
    public boolean contains(Task task) {
        assert task != null : "Task to be checked should not be null";

        return tasks.contains(task);
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

    /**
     * Returns the nearest deadline that is not done yet
     *
     * @param now The time at which the command was given
     * @return The nearest deadline that is not done yet
     */
    public Deadline getUpcomingDeadline(LocalDateTime now) {
        Deadline upcomingDeadline = null;
        LocalDateTime upcomingDeadlineDateTime = null;

        for (Task task : tasks) {
            if (!task.isDone() && task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDateTime deadlineDateTime = deadline.getBy();
                if (upcomingDeadline == null || deadlineDateTime.isBefore(upcomingDeadlineDateTime)) {
                    upcomingDeadline = deadline;
                    upcomingDeadlineDateTime = deadlineDateTime;
                }
            }
        }
        return upcomingDeadline;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
