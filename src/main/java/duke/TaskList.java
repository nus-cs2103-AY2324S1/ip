package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * The `TaskList` class represents a list of tasks in the Duke task management application.
 * It provides methods for adding, deleting, and retrieving tasks, as well as checking if a task exists in the list.
 */
public class TaskList {
    private List<Task> allTasks;

    /**
     * Constructs an empty `TaskList`.
     * The `allTasks` list is initialized as an empty ArrayList.
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    /**
     * Constructs a `TaskList` with an initial list of tasks.
     *
     * @param initialTasks The initial list of tasks to populate the `TaskList`.
     */
    public TaskList(List<Task> initialTasks) {
        allTasks = new ArrayList<>(initialTasks);
    }

    /**
     * Adds a task to the `TaskList`.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        allTasks.add(task);
    }

    /**
     * Deletes a task from the `TaskList` based on its index.
     *
     * @param taskIndex The index of the task to delete.
     */
    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < allTasks.size()) {
            allTasks.remove(taskIndex);
        }
    }


    public void deleteTask(Task task) {
        allTasks.remove(task);
    }

    public int size() {
        return allTasks.size();
    }


    /**
     * Retrieves the list of tasks stored in the `TaskList`.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        return allTasks;
    }

    /**
     * Checks if a task with a specific task type and description exists in the `TaskList`.
     *
     * @param taskType        The type of the task (e.g., "T" for Todo, "D" for Deadline).
     * @param taskDescription The description of the task.
     * @return `true` if a matching task is found, `false` otherwise.
     */
    public boolean isTaskInAllTasks(String taskType, String taskDescription) {
        for (Task task : allTasks) {
            if (task.getTask().equals(taskDescription)
                    && task.getTaskType().equals(taskType)) {
                return true;
            }
        }
        return false;
    }
}
