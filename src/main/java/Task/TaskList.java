package Task;

import Duke.Storage;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new ArrayList<Task>();

    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }
    public void AddTask(Task task) {
        storage.AddTask(task);
        taskList.add(task);
    }

    public void RemoveTask(Task task) {
        storage.RemoveTask(task);
        taskList.remove(task);
    }

    public int Size() {
        return taskList.size();
    }

    public Task GetTask(int i) {
        return taskList.get(i);
    }
}
