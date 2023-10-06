package atlas.components;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import atlas.tasks.Task;

/**
 * List that contains tasks and methods to interact with the tasks
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a TaskList object
     * @param tasks List of tasks to initialise the task list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task into the list
     * @param newTask Task to be added to the list
     */
    public void addTask(Task newTask) {
        assert newTask != null;
        tasks.add(newTask);
    }

    /**
     * Marks tasks as done. Indices that are out of bounds will be skipped.
     * @param indices One or more 0-based task indices
     * @return Tasks marked as done
     */
    public List<Task> markTasks(Integer... indices) {
        return Arrays.stream(indices)
                .filter(idx -> idx >= 0 && idx < tasks.size())
                .map(tasks::get)
                .filter(task -> !task.getTaskStatus())
                .map(Task::markDone)
                .collect(Collectors.toList());
    }

    /**
     * Marks task as not done. Indices that are out of bounds will be skipped.
     * @param indices One or more 0-based task indices
     * @return Tasks marked as undone
     */
    public List<Task> unmarkTasks(Integer... indices) {
        return Arrays.stream(indices)
                .filter(idx -> idx >= 0 && idx < tasks.size())
                .map(tasks::get)
                .filter(Task::getTaskStatus)
                .map(Task::markNotDone)
                .collect(Collectors.toList());
    }

    /**
     * Deletes task from task list. Deleting multiple tasks at once is not supported as deletion is performed
     * using indices, which might be affected by the deletion of previous indices.
     * @param idx Zero-based index of task to delete
     * @return Task deleted
     */
    public Task deleteTask(int idx) {
        Task taskToDelete = tasks.get(idx);
        tasks.remove(idx);
        return taskToDelete;
    }

    /**
     * Returns string containing the current task count in the task list
     * @return Message containing the current task count
     */
    public String getCountString() {
        final String taskPlurality = tasks.size() == 1 ? "labour" : "labours";
        return String.format("Now you have %d %s to do.\n", tasks.size(), taskPlurality);
    }

    /**
     * Returns all tasks occurring on a particular date
     * @param date LocalDate object
     * @return List of tasks that occur on date
     */
    public List<Task> getTaskOnDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.isOccurringOnDate(date))
                .collect(Collectors.toList());
    }

    /**
     * Returns all tasks containing a particular keyword
     * @param keyword Keyword to query for
     * @return List of tasks that contain keyword
     */
    public List<Task> getTasksWithKeyword(String keyword) {
        return tasks.stream()
                .filter(task -> task.hasKeyword(keyword))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    /**
     * Returns all tasks that the user should be reminded of now
     * @return Tasks that user should be reminded of
     */
    public List<Task> getReminders() {
        return tasks.stream()
                .filter(Task::shouldSendReminder)
                .collect(Collectors.toList());
    }
}
