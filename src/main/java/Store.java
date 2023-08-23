import java.util.ArrayList;
import java.util.List;

import error.EkudException;
import task.Task;

public final class Store {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int taskId) {
        Task task = this.getTask(taskId);
        tasks.remove(taskId - 1);
        return task;
    }

    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int taskId) {
        if (taskId < 1 || taskId > tasks.size()) {
            throw new EkudException("Invalid task identiier provided.");
        }
        return tasks.get(taskId - 1);
    }
}
