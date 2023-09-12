package ekud.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks and implements the actions that can be performed
 * on the list of tasks.
 */
public final class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns whether the list is currently not empty.
     * 
     * @return Whether the list is not empty.
     */
    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    /**
     * Returns the task list as a {@link java.util.List}.
     * 
     * @return The task list.
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Returns the task with the given identifier, or null if it doesn't exist.
     * 
     * @param taskId The task identifier.
     * @return The identified task, or null if it doesn't exist.
     */
    public Task getTask(int taskId) {
        if (taskId < 1 || taskId > tasks.size()) {
            return null;
        }
        return tasks.get(taskId - 1);
    }

    /**
     * Adds a new task to the list.
     * 
     * @param task The task to add.
     * @return The task that was added.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the list.
     * 
     * @param taskId The identifier of the task to delete.
     * @return The task that was deleted, or null if it doesn't exist.
     */
    public Task deleteTask(int taskId) {
        Task task = getTask(taskId);
        if (task == null) {
            return null;
        }
        return tasks.remove(taskId - 1);
    }

    /**
     * Finds tasks that match the given query within the task list, returning them
     * as a list.
     * Simply checks if the query is contained within the title of the task.
     * 
     * @param query The search query.
     * @return The list of tasks that match the query.
     */
    public List<Task> findTasks(String query) {
        return asList()
                .stream()
                .filter(task -> task.getTitle().contains(query))
                .collect(Collectors.toList());
    }

    public void deduplicate() {
        tasks = new ArrayList<>(asList().stream().distinct().collect(Collectors.toList()));
    }
}
