package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task  The task to be added.
     */
    public void addTask (Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param taskIndex  The index of the task.
     */
    public void deleteTask (int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        }
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return  The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a task from the TaskList at the specified index.
     *
     * @param index  The index of the task.
     * @return  The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index  The index of the task.
     */
    public void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index  The index of the task.
     */
    public void markTaskAsNotDone(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    /**
     * Finds the tasks that match the keyword.
     *
     * @param keyword  The keywords used to search matching tasks.
     * @return  Tasks that match the keyword.
     */
    public TaskList findTasksByKeyword (String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Loads tasks from a specified file path.
     *
     * @param filePath The path to the file from which tasks will be loaded.
     */
    public void loadTasksFromFile(String filePath) {
        Ui ui = new Ui();
        ui.loadTasks(filePath);
    }
}
