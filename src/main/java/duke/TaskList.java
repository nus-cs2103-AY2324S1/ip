package duke;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList (List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask (Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask (int index) {
        return tasks.get(index);
    }

    public void markTaskAsDone (int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    public void markTaskAsNotDone (int index) {
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
}
