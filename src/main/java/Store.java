import java.util.ArrayList;
import java.util.List;

import task.Task;

public final class Store {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
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
        return tasks.get(taskId - 1);
    }
}
