package geraldbot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import geraldbot.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param idx The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int idx) {
        Task removedTask = this.taskList.remove(idx);
        return removedTask;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param idx The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int idx) {
        Task selectedTask = this.taskList.get(idx);
        return selectedTask;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Finds tasks in the list that match the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks matching the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        return taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
