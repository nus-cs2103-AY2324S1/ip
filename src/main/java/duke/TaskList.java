package duke;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private final List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    public void addTask(Task task) {
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
    public Task getTask(int index) {
        return tasks.get(index);
    }
    public void markTaskAsDone(int index) {
    Task task = tasks.get(index);
    task.markAsDone();
    }

    public void markTaskAsNotDone(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
    }
}
