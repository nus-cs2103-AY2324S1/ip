package chatbot;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task mark(int index) {
        Task task = tasks.get(index);
        task.markTaskDone();
        return task;
    }

    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.markTaskNotDone();
        return task;
    }

    public Task retrieveTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}