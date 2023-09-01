package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a collection of tasks.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves all tasks in the TaskList.
     *
     * @return A list of all tasks in the TaskList.
     */
    public List<Task> getAll() {
        return this.tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added to the TaskList.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Finds tasks in the TaskList containing the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks containing the keyword in their descriptions.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
