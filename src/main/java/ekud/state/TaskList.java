package ekud.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public boolean hasTasks() {
        return !tasks.isEmpty();
    }

    public List<Task> asList() {
        return tasks;
    }

    public Task getTask(int taskId) {
        if (taskId < 1 || taskId > tasks.size()) {
            return null;
        }
        return tasks.get(taskId - 1);
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int taskId) {
        Task task = getTask(taskId);
        if (task == null) {
            return null;
        }
        return tasks.remove(taskId - 1);
    }

    public Task markTask(int taskId) {
        Task task = getTask(taskId);
        if (task == null) {
            return null;
        }
        task.mark();
        return task;
    }

    public Task unmarkTask(int taskId) {
        Task task = getTask(taskId);
        if (task == null) {
            return null;
        }
        task.unmark();
        return task;
    }

    public List<Task> findTasks(String query) {
        return asList()
                .stream()
                .filter(task -> task.getTitle().contains(query))
                .collect(Collectors.toList());
    }
}
