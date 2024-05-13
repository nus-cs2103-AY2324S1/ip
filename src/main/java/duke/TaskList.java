package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Represents a list of tasks to be used by the Duke chat-bot.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Gets the task at the given index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Stringifies the list of tasks for storage for the Duke chat-bot.
     *
     * @return The stringified list of tasks.
     */
    public String stringifyTasks() {
        return tasks.stream().map(Task::encodeTask).collect(Collectors.joining("\n"));
    }

    /**
     * Gets the size of the list of tasks.
     *
     * @return The size of the list of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Finds tasks that match the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A task list containing tasks that match the specified keyword.
     */
    public TaskList findTasks(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.hasMatchingDescription(keyword))
                .collect(Collectors.toList()));
    }

}
